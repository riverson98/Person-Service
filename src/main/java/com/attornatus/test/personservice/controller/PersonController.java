package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.dto.ResponseDto;
import com.attornatus.test.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(this.personService.createPerson(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> allPerson(){
        return ResponseEntity.ok(this.personService.listAllPersons());
    }

    @GetMapping("{personId}")
    public ResponseEntity<PersonDto> findById(@PathVariable int personId){
        return ResponseEntity.ok(this.personService.findById(personId));

    }

    @PutMapping("{personId}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer personId, @RequestBody PersonDto personDto){
        return ResponseEntity.ok(this.personService.updatePerson(personId, personDto));
    }

}
