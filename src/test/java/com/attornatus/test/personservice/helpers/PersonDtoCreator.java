package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.dto.PersonDto;

import java.util.List;

public class PersonDtoCreator {

    public static PersonDto createPersonDto(){
     return PersonDto.builder()
             .personId(2)
             .name("Bora mulher do bill")
             .address(List.of(AddressEntityCreator.createAddressEntity()))
             .build();
    }
}
