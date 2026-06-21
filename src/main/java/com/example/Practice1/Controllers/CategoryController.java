package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.Request.CategoryRequestDto;
import com.example.Practice1.Dtos.Response.CategoryDto;
import com.example.Practice1.Mappers.CategoryMapper;
import com.example.Practice1.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    @GetMapping
    public Iterable<CategoryDto> getAllCategory(
            @RequestParam(required = false, defaultValue = "name") String sort,
            @RequestHeader(name = "auth-token", required = false) String auth
    ){
        if(!Set.of("name").contains(sort)){
            sort = "name";
        }

        return categoryRepository.findAll(Sort.by(sort))
                .stream()
                .map(categoryMapper::toDto)
                .toList();

    }



    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable byte id){
        var category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
            ){
        var category = categoryMapper.toEntity(request);
        categoryRepository.save(category);

        var categoryDto = categoryMapper.toDto(category);

        var uri = uriComponentsBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable(name = "id")byte id,
            @RequestBody CategoryRequestDto request
    ){
        var category = categoryRepository.findById(id).orElse(null);

        if(category == null){
            return ResponseEntity.notFound().build();
        }

        categoryMapper.update(request, category);

        categoryRepository.save(category);

        return ResponseEntity.ok(categoryMapper.toDto(category));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable(name = "id")Byte id
    ){
        var category = categoryRepository.findById(id).orElse(null);

        if(category == null){
            return ResponseEntity.notFound().build();
        }

        categoryRepository.delete(category);

        return ResponseEntity.noContent().build();

    }
}
