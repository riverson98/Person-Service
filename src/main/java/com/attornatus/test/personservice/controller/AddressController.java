package com.attornatus.test.personservice.controller;

import com.attornatus.test.personservice.dto.AddressDto;
import com.attornatus.test.personservice.dto.PersonDto;
import com.attornatus.test.personservice.entity.Address;
import com.attornatus.test.personservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody List<AddressDto> addressDto){
        return ResponseEntity.ok(this.addressService.createAddress(addressDto));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> allAddress(){
        return ResponseEntity.ok(this.addressService.listAllAddresses());
    }

    @PutMapping("{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Integer addressId, @RequestBody AddressDto addressDto){
        addressDto.setAddressId(addressId);
        return ResponseEntity.ok(this.addressService.updateAddress(addressId, addressDto));
    }
}
