package com.attornatus.test.personservice.dto;

import com.attornatus.test.personservice.entity.Person;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressDto {

    private Integer addressId;

    private Integer idPerson;

    private String zip;

    private Boolean mainAddress;

    private String nameOfTheStreet;

    private String number;

    private String city;
}
