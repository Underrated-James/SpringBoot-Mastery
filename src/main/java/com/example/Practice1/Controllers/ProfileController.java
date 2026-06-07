package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.ProfileDto;
import com.example.Practice1.Mappers.ProfileMapper;
import com.example.Practice1.Repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    @Autowired
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @GetMapping
    public Iterable<ProfileDto> getAllProfile(){
        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toDto
                ).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable long id){
        var profile = profileRepository.findById(id).orElse(null);
        if(profile == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profileMapper.toDto(profile));
    }
}
