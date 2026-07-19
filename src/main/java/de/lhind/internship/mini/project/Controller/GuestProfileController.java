package de.lhind.internship.mini.project.Controller;


import de.lhind.internship.mini.project.Entity.GuestProfile;
import de.lhind.internship.mini.project.Service.GuestProfileService;
import de.lhind.internship.mini.project.dto.GuestProfileDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/guests/{guestId}/profile")
public class GuestProfileController {

    private GuestProfileService guestProfileService;

    @PostMapping
    private ResponseEntity<GuestProfileDTO> save(@PathVariable("guestId") Long id, @RequestBody GuestProfileDTO guestProfileDTO){
        return guestProfileService.createProfile(id,guestProfileDTO);
    }
}
