package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;
import com.attornatus.test.personservice.repository.AddressRepository;
import com.attornatus.test.personservice.repository.PersonRepository;
import com.attornatus.test.personservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;


    public PersonDto createPerson(PersonDto dto) {
        var personEntity = EntityDtoUtil.toPersonEntity(dto);
        this.personRepository.save(personEntity);
        return saveAddress(personEntity);
    }

    public PersonDto updatePerson(int id, PersonDto dto) {
        Person person = this.personRepository.findById(id)
                .map(p -> dto)
                .map(EntityDtoUtil::toPersonEntity)
                .orElse(null);
        Objects.requireNonNull(person).setPersonId(id);

        Person personToBeSaved = this.personRepository.save(person);
        saveAddress(personToBeSaved);
        return EntityDtoUtil.toPersonDto(person);
    }

    public List<PersonDto> listAllPersons() {
         return personRepository.findAll()
                .stream()
                .map(EntityDtoUtil::toPersonDto)
                .collect(Collectors.toList());
    }

    public PersonDto findById(int personId) {
        return personRepository.findById(personId)
                .map(EntityDtoUtil::toPersonDto)
                .orElse(null);
    }

    private PersonDto saveAddress(Person personEntity) {
        var address = personEntity.getAddress();
        address.forEach(it -> it.setpId(personEntity));
        Address addresses = address.get(0);
        this.addressRepository.save(addresses);

        return EntityDtoUtil.toPersonDto(personEntity);
    }
}
