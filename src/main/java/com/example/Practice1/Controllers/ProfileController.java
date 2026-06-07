package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.ProfileDto;
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
    public Iterable<ProfileDto> getAllProfile(){
        return profileRepository.findAll()
                .stream()
                .map(profile -> new ProfileDto(
                        profile.getId(),
                        profile.getBio(),
                        profile.getHabbits(),
                        profile.getDateOfBirth(),
                        profile.getLoyaltyPoints(),
                        profile.getUser().getId())
                ).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable long id){
        var profile = profileRepository.findById(id).orElse(null);
        if(profile == null){
            return ResponseEntity.notFound().build();
        }
        var profileDto = new ProfileDto(profile.getId(), profile.getBio(), profile.getHabbits(), profile.getDateOfBirth(), profile.getLoyaltyPoints(), profile.getUser().getId());
        return ResponseEntity.ok(profileDto);
    }
}
