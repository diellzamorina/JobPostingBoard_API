package com.example.jobpostingboard_api.controller;


import com.example.jobpostingboard_api.dto.AddressRequestDto;
import com.example.jobpostingboard_api.entity.Address;
import com.example.jobpostingboard_api.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {


    private final AddressService addressService;
    @PostMapping("")
    public ResponseEntity<Address> addNewAddress(@RequestBody AddressRequestDto addressRequestDto){
        var address = addressService.addNewAddress(addressRequestDto);
       if(address!=null){
           return ResponseEntity.ok(address);
       }
       else{
           return ResponseEntity.notFound().build();
       }

    }

    @GetMapping("/all")
    public  ResponseEntity<List<Address>> getAllAddresses(){
        var addresses = addressService.getAllAddresses();
        if(addresses.size()!=0){
            return ResponseEntity.ok(addresses);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") int id){
        var address = addressService.findAddressById(id);
        if(address!=null){
            return ResponseEntity.ok(addressService.findAddressById(id));
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddressById(@PathVariable("id") int id, @RequestBody AddressRequestDto addressRequestDto){
       var address = addressService.updateAddressById(id, addressRequestDto);

        if(address!=null){
            return ResponseEntity.ok(address);
        }
        else{
            return  ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable int id){
        var address = addressService.deleteAddressById(id);
        if(address !=null){
            return ResponseEntity.ok(address);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
