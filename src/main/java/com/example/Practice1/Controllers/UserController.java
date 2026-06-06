package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.UserDto;
import com.example.Practice1.Entities.User;
import com.example.Practice1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<UserDto> getAllUsers(){
        System.out.print("Users endpoint");
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProfile(), user.getAddresses(), user.getFavoriteProducts()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){
       var user = userRepository.findById(id).orElse(null);
       if(user == null){
           return ResponseEntity.notFound().build();
       }
       var userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProfile(), user.getAddresses(), user.getFavoriteProducts());
       return ResponseEntity.ok(userDto);
    }

}
