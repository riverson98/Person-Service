package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.entity.Person;
import com.attornatus.test.personservice.repository.AddressRepository;
import com.attornatus.test.personservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        return EntityDtoUtil.toAddressDto(address);
    }

    public List<AddressDto> listAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(EntityDtoUtil::toAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDto updateAddress(int id, AddressDto dto) {
        var address = addressRepository.findById(id)
                .map(it -> EntityDtoUtil.toAddressEntity(dto))
                .orElse(null);
        this.addressRepository.save(Objects.requireNonNull(address));

        return EntityDtoUtil.toAddressDto(address);
    }
}
