
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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addressId;
   String addressLine1;
    String addressLine2;
    String addressLine3;
    String landMark;
    String pinCode;
    String state;
    String addressType;
}

