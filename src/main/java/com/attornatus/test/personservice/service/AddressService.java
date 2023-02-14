package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;
import com.attornatus.test.personservice.repository.AddressRepository;
import com.attornatus.test.personservice.util.EntityDtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonService personService;

    public AddressDto createAddress(List<AddressDto> addressDto) {

        PersonDto byId = personService.findById(addressDto.get(0).getIdPerson());
        Person person = EntityDtoUtil.toPersonEntity(byId);
        Address address = EntityDtoUtil.toAddressEntity(addressDto.get(0));
        address.setpId(person);
        addressRepository.save(address);

        log.info("Create address to person id {} with id {}", byId.getPersonId(),
                address.getAddressId());

        return EntityDtoUtil.toAddressDto(address);
    }
}
