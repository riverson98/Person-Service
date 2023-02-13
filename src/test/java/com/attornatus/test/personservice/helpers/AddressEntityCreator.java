package com.attornatus.test.personservice.helpers;

import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;

public class AddressEntityCreator {

    public static Address createAddressEntity(){
        Address address = new Address();
        address.setZip("00000-000");
        address.setMainAddress(Boolean.TRUE);
        address.setCity("Itajai");
        return address;
    }
}
