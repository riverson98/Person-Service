package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.exceptions.BadRequestException;
import com.attornatus.test.personservice.exceptions.NotFoundException;
import com.attornatus.test.personservice.helpers.AddressEntityCreator;
import com.attornatus.test.personservice.helpers.PersonEntityCreator;
import com.attornatus.test.personservice.repository.AddressRepository;
import com.attornatus.test.personservice.repository.PersonRepository;
import com.attornatus.test.personservice.service.PersonService;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PersonService personService;

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

        Mockito.when(personRepository.findAll())
                .thenReturn(List.of(PersonEntityCreator.createPersonEntity()));
    }


    @Test
    void createPerson_ReturnPersonDto_WhenIsSuccessfully() throws Exception {
            URI uri = new URI("/person");
            String json = "{\"idPessoa\":\"1\",\"nome\":\"riverson\",\"datNascimento\":\"01/01/1998\"," +
                    "\"enderecos\":[{\"idEndereco\":\"1\", \"cep\":\"00000-000\",\"endPrincipal\":false," +
                    "\"rua\":\"rua dos sonhos\",\"numero\":15,\"cidade\":\"itajai\"}]}";

            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post(uri)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.idPessoa", Matchers.is(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("riverson")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.datNascimento", Matchers.is("01/01/1998")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].idEndereco", Matchers.is(1)))
                    .andDo(System.out::println)
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cep", Matchers.is("00000-000")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].endPrincipal", Matchers.is(false)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].rua", Matchers.is("rua dos sonhos")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].numero", Matchers.is("15")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cidade", Matchers.is("itajai")));
    }

    @Test
    void createPerson_ReturnBadRequest_WhenSomeItemIsBlank() throws Exception {
            URI uri = new URI("/person");
            String json = "{\"nome\":\"\",\"datNascimento\":\"01/01/1998\"," +
                    "\"enderecos\":[{\"cep\":\"00000-000\",\"endPrincipal\":false," +
                    "\"rua\":\"rua dos sonhos\",\"numero\":15,\"cidade\":\"itajai\"}]}";

        Mockito.when(personRepository.save(Mockito.any()))
                .thenThrow(BadRequestException.class);

            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post(uri)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(BadRequestException.HTTP_STATUS_CODE))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(BadRequestException.ERROR_CODE)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(BadRequestException.DEFAULT_MESSAGE)));
    }

    @Test
    void allPerson_ReturnListOfPersonDto_WhenPersonExistInDatabase() throws Exception {
        URI uri = new URI("/person");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPessoa", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Bora Bill")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].datNascimento", Matchers.is("01/01/1998")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].idEndereco", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].cep", Matchers.is("00000-000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].endPrincipal", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].rua", Matchers.is("rua dos sonhos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].numero", Matchers.is("15")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].enderecos[0].cidade", Matchers.is("itajai")));
    }

    @Test
    void allPerson_ReturnEmptyList_WhenDataBaseIsEmpty() throws Exception {
        URI uri = new URI("/person");

        Mockito.when(personRepository.findAll())
                .thenReturn(List.of());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", Matchers.is(List.of())));


    }

    @Test
    void findById_ReturnPersonDto_WhenIdExist() throws Exception {

        URI uri = new URI("/person/1");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPessoa", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Bora Bill")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.datNascimento", Matchers.is("01/01/1998")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].idEndereco", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cep", Matchers.is("00000-000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].endPrincipal", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].rua", Matchers.is("rua dos sonhos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].numero", Matchers.is("15")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cidade", Matchers.is("itajai")));
    }

    @Test
    void findById_ReturnNotFoundException_WhenPersonIdDoesExist() throws Exception {

        URI uri = new URI("/person/2");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(NotFoundException.HTTP_STATUS_CODE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(NotFoundException.ERROR_CODE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(NotFoundException.DEFAULT_MESSAGE)));
    }

    @Test
    void updatePerson_ReturnPersonDto_WhenPersonExist() throws Exception {

        URI uri = new URI("/person/1");
        String json = "{\"nome\":\"V\",\"datNascimento\":\"01/01/2077\"," +
                "\"enderecos\":[{\"idEndereco\":\"1\",\"cep\":\"00000-000\",\"endPrincipal\":true," +
                "\"rua\":\"Pacifica\",\"numero\":\"00\",\"cidade\":\"Night City\"}]}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPessoa", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("V")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.datNascimento", Matchers.is("01/01/2077")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].idEndereco", Matchers.is(1)))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cep", Matchers.is("00000-000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].endPrincipal", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].rua", Matchers.is("Pacifica")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].numero", Matchers.is("00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enderecos[0].cidade", Matchers.is("Night City")));


    }
    @Test
    void updatePerson_ReturnNotFoundException_WhenPersonDoesExist() throws Exception {

        URI uri = new URI("/person/2");
        String json = "{\"nome\":\"V\",\"datNascimento\":\"01/01/2077\"," +
                "\"enderecos\":[{\"idEndereco\":\"1\",\"cep\":\"00000-000\",\"endPrincipal\":true," +
                "\"rua\":\"Pacifica\",\"numero\":\"00\",\"cidade\":\"Night City\"}]}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(NotFoundException.HTTP_STATUS_CODE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(NotFoundException.ERROR_CODE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(NotFoundException.DEFAULT_MESSAGE)));
    }
}