package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.Request.UpdateDtos.UserUpdateDto;
import com.example.Practice1.Dtos.Request.UserRequestDto;
import com.example.Practice1.Dtos.Response.UserDto;
import com.example.Practice1.Mappers.UserMapper;
import com.example.Practice1.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(name = "auth-token") String auth,
            @RequestParam(required = false, defaultValue = "") String sort
    ){
        System.out.print("the auth token value is " + auth);

        System.out.println("data is sorted by " + sort);


        if(!Set.of("name", "email").contains(sort)){
            sort = "name";
        }
        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){
       var user = userRepository.findById(id).orElse(null);
       if(user == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(userMapper.toDto(user));
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
            ) {
        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);

        System.out.println(user);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id")Long id,
            @RequestBody UserUpdateDto request
            ){
        var user = userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        userMapper.update(request, user);

        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable(name = "id")Long id
    ){
        var user = userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);

        return ResponseEntity.noContent().build();

    }

}
