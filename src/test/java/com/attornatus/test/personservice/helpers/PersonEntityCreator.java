package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.entity.Person;

public class PersonEntityCreator {

    public static Person createPersonEntity(){
        Person person = new Person();
        person.setPersonId(1);
        person.setName("Bora Bill");
        return person;
    }
}
