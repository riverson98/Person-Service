package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.helpers.AddressDtoCreator;
import com.attornatus.test.personservice.helpers.AddressEntityCreator;
import com.attornatus.test.personservice.repository.AddressRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    private final Address addressEntity = AddressEntityCreator.createAddressEntity();

    private final AddressDto addressDto = AddressDtoCreator.createAddressDto();

    @Test
    @DisplayName("createAddress should return a address")
    void createAddress_ReturnAddressDto_WhenSuccessfully() {
        Mockito.when(addressRepository.save(Mockito.any()))
                .thenReturn(addressEntity);

        AddressDto dto = addressService.createAddress(addressDto);

        assertThat(dto).isNotNull()
                .extracting("zip", "mainAddress", "city")
                .contains("00000-000", true, "Itajai");
    }

    @Test
    @DisplayName("ListAllAddress should return a list of addresses")
    void listAllAddresses_ReturnListOfAddress_WhenSuccessfully() {
        Mockito.when(addressRepository.findAll())
                .thenReturn(List.of(addressEntity));

        List<AddressDto> dto = addressService.listAllAddresses();

        assertThat(dto).isNotNull()
                .hasSize(1)
                .extracting("zip", "mainAddress", "city")
                .contains(tuple("00000-000", true, "Itajai"));
    }
}