package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.Response.ProductDto;
import com.example.Practice1.Mappers.ProductMapper;
import com.example.Practice1.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "id") String sort
    ){

        if(!Set.of("name", "price", "quantity").contains(sort)){
            sort = "name";
        }
    return productRepository.findAll(Sort.by(sort))
            .stream()
            .map(productMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){

        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
