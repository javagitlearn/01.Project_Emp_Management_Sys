package com.nmbr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long dId;
    String designation;

    @OneToOne(mappedBy = "designation")
    @JsonIgnore
    private Employee employee;
}
