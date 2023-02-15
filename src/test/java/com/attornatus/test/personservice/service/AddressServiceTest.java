package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.helpers.AddressDtoCreator;
import com.attornatus.test.personservice.helpers.AddressEntityCreator;
import com.attornatus.test.personservice.helpers.PersonDtoCreator;
import com.attornatus.test.personservice.repository.AddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PersonService personService;

    private final Address addressEntity = AddressEntityCreator.createAddressEntity();

    private final AddressDto addressDto = AddressDtoCreator.createAddressDto();

    private final PersonDto personDto = PersonDtoCreator.createPersonDto();

    @Test
    @DisplayName("createAddress should return a address")
    void createAddress_ReturnAddressDto_WhenSuccessfully() {
        Mockito.when(addressRepository.save(Mockito.any()))
                .thenReturn(addressEntity);
        Mockito.when(personService.findById(Mockito.anyInt()))
                .thenReturn(personDto);

        AddressDto dto = addressService.createAddress(List.of(addressDto));

        assertThat(dto).isNotNull()
                .extracting("zip", "mainAddress", "city")
                .contains("00000-000", true, "Itajai");
    }
}