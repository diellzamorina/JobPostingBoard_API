package com.example.jobpostingboard_api.service;


import com.example.jobpostingboard_api.dto.AddressRequestDto;
import com.example.jobpostingboard_api.entity.Address;
import com.example.jobpostingboard_api.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    
    private final AddressRepository addressRepository;



    public Address addNewAddress(AddressRequestDto addressRequestDto) {
        try{
            var address = new Address();
            address.setCity(addressRequestDto.getCity());
            address.setState(addressRequestDto.getState());
            address.setStreet(addressRequestDto.getStreet());
            address.setZipCode(addressRequestDto.getZipCode());

            addressRepository.save(address);
            return address;
        }
        catch (Exception e){
            return null;
        }


    }

    public Address findAddressById(Integer id){
       return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address updateAddressById(int id, AddressRequestDto addressRequestDto) {

        var address = findAddressById(id);
        if(address != null){
            try {
                address.setZipCode(addressRequestDto.getZipCode());
                address.setStreet(addressRequestDto.getStreet());
                address.setState(addressRequestDto.getState());
                address.setCity(addressRequestDto.getCity());

                addressRepository.save(address);
                return address;
            }
            catch (Exception e){
                return null;
            }

        }
        else{
            return null;
        }

    }

    public String deleteAddressById(int id) {

        addressRepository.deleteById(id);
        return "Address deleted successfully";
    }
}
