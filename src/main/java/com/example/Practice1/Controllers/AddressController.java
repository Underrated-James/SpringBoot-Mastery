package com.example.Practice1.Controllers;

import com.example.Practice1.Entities.Address;
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
    public Iterable<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id){
        var address = addressRepository.findById(id).orElse(null);
        if(address == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }
}
