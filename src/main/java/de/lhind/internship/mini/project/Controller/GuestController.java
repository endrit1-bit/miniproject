package de.lhind.internship.mini.project.Controller;

import de.lhind.internship.mini.project.Service.GuestService;
import de.lhind.internship.mini.project.dto.GuestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/guests")
@RestController
@AllArgsConstructor
public class GuestController {

    private GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDTO> save(@Valid @RequestBody GuestDTO guestDTO){
        return guestService.createGuest(guestDTO);
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests(){
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable("id") Long guestId){
        return guestService.getById(guestId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable("id") Long guestId,@Valid @RequestBody GuestDTO guestDTO){
        return guestService.updateGuest(guestId,guestDTO);
    }







}
