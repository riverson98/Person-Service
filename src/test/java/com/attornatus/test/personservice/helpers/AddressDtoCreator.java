package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;

public class AddressDtoCreator {

    public static AddressDto createAddressDto(){
     return AddressDto.builder()
             .idPerson(1)
             .zip("00000-000")
             .mainAddress(Boolean.TRUE)
             .city("Itajai")
             .build();
    }
}
