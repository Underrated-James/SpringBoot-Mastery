package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.ProductDto;
import com.example.Practice1.Entities.Product;
import com.example.Practice1.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(){
    return productRepository.findAll()
            .stream()
            .map(product -> new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory().getId()))
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){

        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        var productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory().getId());
        return ResponseEntity.ok(productDto);
    }
}
