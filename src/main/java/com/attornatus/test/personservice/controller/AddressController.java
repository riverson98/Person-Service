package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody @Valid List<AddressDto> addressDto){
        return ResponseEntity.ok(this.addressService.createAddress(addressDto));
    }

}
