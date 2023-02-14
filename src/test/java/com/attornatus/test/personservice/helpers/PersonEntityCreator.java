package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.entity.Person;

import java.util.List;

public class PersonEntityCreator {

    public static Person createPersonEntity(){
        Person person = new Person();
        person.setPersonId(1);
        person.setName("Bora Bill");
        person.setAddress(List.of(AddressEntityCreator.createAddressEntity()));
        return person;
    }
}
