package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.Request.SellerRequestDto;
import com.example.Practice1.Dtos.SellerDto;
import com.example.Practice1.Mappers.ProductMapper;
import com.example.Practice1.Mappers.SellerMapper;
import com.example.Practice1.Repositories.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/sellers")
@AllArgsConstructor
public class SellerController {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @GetMapping
    public Iterable<SellerDto> getAllSellers(
            @RequestParam(required = false, defaultValue = "")String sort
    ){
        if(!Set.of("name", "storeName").contains(sort)){
            sort = "name";
        }

        return sellerRepository.findAll(Sort.by(sort))
                .stream()
                .map(sellerMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> getSellerById(@PathVariable long id){
        var seller = sellerRepository.findById(id).orElse(null);
        if(seller == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sellerMapper.toDto(seller));
    }

    @PostMapping
    public ResponseEntity<SellerDto> createSeller(
            @RequestBody SellerRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
            ){
        var seller = sellerMapper.toEntity(request);
        sellerRepository.save(seller);
        var sellertDto = sellerMapper.toDto(seller);

        var uri = uriComponentsBuilder.path("/sellers/{id}").buildAndExpand(sellertDto.getId()).toUri();
        return ResponseEntity.created(uri).body(sellertDto);
    }
}
