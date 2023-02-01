package com.nmbr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private String gender;
    private String mobile;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="employee_id")
    List <Client> client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="designation_id")
    Designation designation;

    String reportingManager;
    Boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

}
