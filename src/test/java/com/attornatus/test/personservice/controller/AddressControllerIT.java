package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.exceptions.BadRequestException;
import com.attornatus.test.personservice.exceptions.NotFoundException;
import com.attornatus.test.personservice.helpers.AddressEntityCreator;
import com.attornatus.test.personservice.helpers.PersonEntityCreator;
import com.attornatus.test.personservice.repository.AddressRepository;
import com.attornatus.test.personservice.repository.PersonRepository;
import com.attornatus.test.personservice.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private AddressService addressService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private AddressRepository addressRepository;


    @BeforeEach
    void mockitoSetup(){
        Mockito.when(personRepository.findById(1))
                .thenReturn(Optional.of(PersonEntityCreator.createPersonEntity()));

        Mockito.when(personRepository.save(Mockito.any()))
                .thenReturn(PersonEntityCreator.createPersonEntity());

        Mockito.when(addressRepository.save(Mockito.any()))
                .thenReturn(AddressEntityCreator.createAddressEntity());
    }

    @Test
    void createAddress_returnAddressToPersonDto_WhenIsSuccessfully() throws Exception {
        URI uri = new URI("/address");
        String json = "[{\"idPessoa\": \"1\", \"cep\": \"000\", \"endPrincipal\": true, " +
                "\"rua\": \"Weston\", \"numero\": \"213\", \"cidade\": \"night city\"}]";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPessoa", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep", Matchers.is("000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endPrincipal", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua", Matchers.is("Weston")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero", Matchers.is("213")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade", Matchers.is("night city")));

    }
    @Test
    void createAddress_returnNotFoundException_WhenPersonIdDoesExist() throws Exception {
        URI uri = new URI("/address");
        String json = "[{\"idPessoa\": \"2\", \"cep\": \"000\", \"endPrincipal\": true, " +
                "\"rua\": \"Weston\", \"numero\": \"213\", \"cidade\": \"night city\"}]";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(NotFoundException.HTTP_STATUS_CODE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(NotFoundException.ERROR_CODE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(NotFoundException.DEFAULT_MESSAGE)));

    }

    @Test
    void createAddress_returnBadRequestException_WhenRequestHasSomeItemBlank() throws Exception {
        URI uri = new URI("/address");
        String json = "[{\"idPessoa\": \"1\", \"cep\": \"\", \"endPrincipal\": true, " +
                "\"rua\": \"Weston\", \"numero\": \"213\", \"cidade\": \"night city\"}]";

        Mockito.when(addressRepository.save(Mockito.any()))
                .thenThrow(BadRequestException.class);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(BadRequestException.HTTP_STATUS_CODE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(BadRequestException.ERROR_CODE)));

    }
}