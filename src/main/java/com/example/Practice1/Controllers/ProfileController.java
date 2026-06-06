package com.example.Practice1.Controllers;


import com.example.Practice1.Entities.Profile;
import com.example.Practice1.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    public Iterable<Profile> getAllProfile(){
        return profileRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable long id){
        var profile = profileRepository.findById(id).orElse(null);
        if(profile == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }
}
