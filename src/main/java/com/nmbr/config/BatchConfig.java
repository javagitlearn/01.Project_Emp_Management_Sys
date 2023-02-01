package com.nmbr.config;

import com.nmbr.entities.Employee;
import com.nmbr.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor

public class BatchConfig {

    @Autowired

    private EmployeeRepo employeeRepo;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader reader(){
        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader();

        itemReader.setResource(new FileSystemResource("src/main/recourses/employee.csv"));
        itemReader.setName("employeeCsv");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return  itemReader;
    }

    private LineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(" , ");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","name","age","gender","mobile","clId","clientName","isActive","designation","reportingManager","isActive","addressId","addressLine1","addressLine2","addressLine3","landMark","pinCode","state","addressType");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public EmployeeItemProcessor processor(){
        return new EmployeeItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Employee> writer(){
        RepositoryItemWriter<Employee> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(employeeRepo);
        itemWriter.setMethodName("save");
        return  itemWriter;
    }

    @Bean
    public Step step1(){
      return   stepBuilderFactory.get("csv-step").<Employee,Employee> chunk(10)
              .reader(reader())
              .processor(processor())
              .writer(writer())
              .build();
    }
    @Bean
    public Job job(){
        return jobBuilderFactory.get("impost_csv")
                .flow(step1())
                .end()
                .build();
    }
}
