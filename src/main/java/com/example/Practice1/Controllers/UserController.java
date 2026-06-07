package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.UserDto;
import com.example.Practice1.Entities.User;
import com.example.Practice1.Mappers.UserMapper;
import com.example.Practice1.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(){
        System.out.print("Users endpoint");
        return userRepository.findAll()
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

}
