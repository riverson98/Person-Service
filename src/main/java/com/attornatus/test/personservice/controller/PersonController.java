package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonDto createPerson(@RequestBody @Valid PersonDto personDto){
        log.info("receiving request {}", personDto);
        return this.personService.createPerson(personDto);
    }

    @GetMapping
    public List<PersonDto> allPerson(){
        return this.personService.listAllPersons();
    }

    @GetMapping("{personId}")
    public PersonDto findById(@PathVariable int personId){
        return this.personService.findById(personId);

    }

    @PutMapping("{personId}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer personId, @RequestBody @Valid PersonDto personDto){
        log.info("update request {}", personDto);
        return ResponseEntity.ok(this.personService.updatePerson(personId, personDto));
    }

}
