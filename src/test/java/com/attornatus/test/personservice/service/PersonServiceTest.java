package com.attornatus.test.personservice.service;

import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Person;
import com.attornatus.test.personservice.helpers.PersonDtoCreator;
import com.attornatus.test.personservice.helpers.PersonEntityCreator;
import com.attornatus.test.personservice.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private final PersonDto personDto = PersonDtoCreator.createPersonDto();

    private final Person personEntity = PersonEntityCreator.createPersonEntity();

    @Test
    @DisplayName("savePerson: should return a Person")
    void savePerson_ReturnPersonDto_WhenIsSuccessfully() {
        Mockito.when(personRepository.save(Mockito.any()))
                .thenReturn(personEntity);

        PersonDto dto = personService.createPerson(personDto);

        assertThat(dto).isNotNull()
                .extracting("name")
                .isEqualTo("Bora mulher do bill");
    }

    @Test
    @DisplayName("updatePerson: must update a person's data")
    void updatePerson_ReturnPersonDto_WhenIsSuccessfully() {
        Mockito.when(personRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(personEntity));

        PersonDto dto = personService.updatePerson(1, this.personDto);

        assertThat(dto).extracting("id", "name")
                .contains(2, "Bora mulher do bill");

    }

    @Test
    @DisplayName("ListAllAddress should return a list of addresses")
    void listAllPersons_ReturnListOfPersonDto_WhenIsSuccessfully() {
        Mockito.when(personRepository.findAll())
                .thenReturn(List.of(personEntity));

        List<PersonDto> dto = personService.listAllPersons();

        assertThat(dto).isNotNull()
                .hasSize(1)
                .extracting("name")
                .contains("Bora Bill");

    }
}