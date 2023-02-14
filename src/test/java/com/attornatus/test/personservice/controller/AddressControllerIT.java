package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.helpers.PersonEntityCreator;
import com.attornatus.test.personservice.repository.PersonRepository;
import com.attornatus.test.personservice.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void mockitoSetup(){


        Mockito.when(personRepository.save(Mockito.any()))
                .thenReturn(PersonEntityCreator.createPersonEntity());
    }

    @Test
    void createAddress_returnPersonDto_WhenIsSuccessfully() throws Exception {
        URI uri = new URI("/address");
        String json = "[{\"idPessoa\": \"1\", \"cep\": \"000\", \"endPrincipal\": true, " +
                "\"rua\": \"Weston\", \"numero\": \"213\", \"cidade\": \"night city\"}]";

        Mockito.when(personRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(PersonEntityCreator.createPersonEntity()));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}