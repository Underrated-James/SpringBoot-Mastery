package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.AddressDto;
import com.example.Practice1.Mappers.AddressMapper;
import com.example.Practice1.Repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    @Autowired
    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    @GetMapping
    public Iterable<AddressDto> getAllAddress(){

        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable long id){
        var address = addressRepository.findById(id).orElse(null);
        if(address == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressMapper.toDto(address));
    }
}
