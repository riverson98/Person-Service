package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.entity.Address;

public class AddressEntityCreator {

    public static Address createAddressEntity(){
        Address address = new Address();
        address.setAddressId(1);
        address.setZip("00000-000");
        address.setNameOfTheStreet("rua dos sonhos");
        address.setNumber("15");
        address.setMainAddress(Boolean.TRUE);
        address.setCity("itajai");
        return address;
    }
}
