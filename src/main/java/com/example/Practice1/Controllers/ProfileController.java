package com.example.Practice1.Controllers;


import com.example.Practice1.Dtos.ProfileDto;
import com.example.Practice1.Dtos.Request.ProfileRequestDto;
import com.example.Practice1.Entities.Profile;
import com.example.Practice1.Mappers.ProfileMapper;
import com.example.Practice1.Repositories.ProfileRepository;
import com.example.Practice1.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    @Autowired
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;

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

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
            ){
        // Fetch the User for the profile
        var user = userRepository.findById(request.getUserId()).orElse(null);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        
        // Manually construct the Profile with the User association first
        // This ensures proper initialization with @MapsId relationship
        var profile = Profile.builder()
                .user(user)
                .bio(request.getBio())
                .habbits(request.getHabbits())
                .dateOfBirth(request.getDateOfBirth())
                .loyaltyPoints(request.getLoyaltyPoints())
                .build();
        
        profileRepository.save(profile);

        var profileDto = profileMapper.toDto(profile);

        var uri = uriComponentsBuilder.path("/profile/{id}").build().toUri();

        return ResponseEntity.created(uri).body(profileDto);
    }
}
