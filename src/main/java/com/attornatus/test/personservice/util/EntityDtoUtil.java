package com.attornatus.test.personservice.util;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.dto.ResponseDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class EntityDtoUtil {

    private EntityDtoUtil() {
        throw new UnsupportedOperationException("Its a util Class");
    }

    public static Person toPersonEntity(PersonDto dto){
        var person = new Person();
        BeanUtils.copyProperties(dto, person);
        return person;
    }

    public static ResponseDto toResponse(Person person){
        var response = new ResponseDto();
        response.setId(person.getPersonId());
        response.setName(person.getName());
        response.setAddress(person.getAddress());
        response.setBirth(person.getBirth());
        response.setIdPerson(person.getPersonId());
        return response;
    }

    public static PersonDto toPersonDto(Person person){
        var dto = new PersonDto();
        BeanUtils.copyProperties(person, dto);
        return dto;
    }

    public static List<Address> toAddressEntitya(List<AddressDto> addresses, Person person){
        var addressEntity = new Address();
        AddressDto address = addresses.get(0);
        addressEntity.setpId(person);
        addressEntity.setMainAddress(address.getMainAddress());
        addressEntity.setCity(address.getCity());
        addressEntity.setNameOfTheStreet(address.getNameOfTheStreet());
        addressEntity.setZip(address.getZip());
        addressEntity.setNumber(address.getNumber());
        return List.of(addressEntity);
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
