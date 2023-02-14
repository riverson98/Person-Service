package com.attornatus.test.personservice.util;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    private EntityDtoUtil() {
        throw new UnsupportedOperationException("Its a util Class");
    }

    public static Person toPersonEntity(PersonDto dto){
        var person = new Person();
        BeanUtils.copyProperties(dto, person);
        return person;
    }

    public static PersonDto toPersonDto(Person person){
        var dto = new PersonDto();
        BeanUtils.copyProperties(person, dto);
        return dto;
    }

    public static Address toAddressEntity(AddressDto dto){
        var address = new Address();
        BeanUtils.copyProperties(dto, address);
        return address;
    }

    public static AddressDto toAddressDto(Address address){
        var dto = new AddressDto();
        BeanUtils.copyProperties(address, dto);
        dto.setIdPerson(address.getpId().getPersonId());
        return dto;
    }
}
