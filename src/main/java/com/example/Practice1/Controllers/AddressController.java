package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.AddressDto;
import com.example.Practice1.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public Iterable<AddressDto> getAllAddress(){

        return addressRepository.findAll()
                .stream()
                .map(address -> new AddressDto(address.getId(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getCountry(),
                        address.getUser().getId()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable long id){
        var address = addressRepository.findById(id).orElse(null);
        if(address == null){
            return ResponseEntity.notFound().build();
        }
        var addressDto = new AddressDto(address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getUser().getId());

        return ResponseEntity.ok(addressDto);
    }
}
