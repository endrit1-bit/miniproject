package de.lhind.internship.mini.project.Service;


import de.lhind.internship.mini.project.Entity.Guest;
import de.lhind.internship.mini.project.Entity.GuestProfile;
import de.lhind.internship.mini.project.Repository.GuestProfileRepository;
import de.lhind.internship.mini.project.Repository.GuestRepository;
import de.lhind.internship.mini.project.dto.GuestProfileDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestProfileService {

    private GuestProfileRepository guestProfileRepository;
    private GuestRepository guestRepository;

    public ResponseEntity<GuestProfileDTO> createProfile(Long guestId, GuestProfileDTO guestProfileDTO) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GuestProfile guestProfile = new GuestProfile();
        guestProfile.setAddress(guestProfileDTO.getAddress());
        guestProfile.setNationality(guestProfileDTO.getNationality());
        guestProfile.setDateOfBirth(guestProfileDTO.getDateOfBirth());
        guestProfile.setPreferredLanguage(guestProfileDTO.getPreferredLanguage());

        Guest existingGuest = guest.get();
        existingGuest.setGuestProfile(guestProfile);

        Guest savedGuest = guestRepository.save(existingGuest);
        GuestProfile savedGuestProfile = savedGuest.getGuestProfile();

        guestProfileDTO.setId(savedGuestProfile.getId());
        guestProfileDTO.setAddress(savedGuestProfile.getAddress());
        guestProfileDTO.setNationality(savedGuestProfile.getNationality());
        guestProfileDTO.setPreferredLanguage(savedGuestProfile.getPreferredLanguage());
        guestProfileDTO.setDateOfBirth(savedGuestProfile.getDateOfBirth());


        return new ResponseEntity<>(guestProfileDTO,HttpStatus.CREATED);
    }
}
