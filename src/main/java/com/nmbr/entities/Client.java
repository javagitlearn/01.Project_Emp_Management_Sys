package com.nmbr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long clId;
    String clientName;
    Boolean clientIsActive;

    @ManyToOne
    @JsonIgnore
    private Employee employee;
}