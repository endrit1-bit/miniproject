package de.lhind.internship.mini.project.Service;

import de.lhind.internship.mini.project.Entity.Guest;
import de.lhind.internship.mini.project.Entity.GuestProfile;
import de.lhind.internship.mini.project.Repository.GuestProfileRepository;
import de.lhind.internship.mini.project.Repository.GuestRepository;
import de.lhind.internship.mini.project.dto.GuestDTO;
import de.lhind.internship.mini.project.exception.DuplicateEmailException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestRepository guestRepository;

    public ResponseEntity<GuestDTO> createGuest(GuestDTO guestDTO) {

        Guest guest = new Guest();
        if (guestRepository.existsByEmail(guestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already in use: " + guestDTO.getEmail());
        }


        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setEmail(guestDTO.getEmail());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        Guest savedGuest = guestRepository.save(guest);

        guestDTO.setId(savedGuest.getId());
        guestDTO.setFirstName(savedGuest.getFirstName());
        guestDTO.setLastName(savedGuest.getLastName());
        guestDTO.setEmail(savedGuest.getEmail());
        guestDTO.setPhoneNumber(savedGuest.getPhoneNumber());

        return new ResponseEntity<>(guestDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();

        List<GuestDTO> guestDTO = guests.stream()
                .map(guest -> GuestDTO.builder()
                        .id(guest.getId())
                        .firstName(guest.getFirstName())
                        .lastName(guest.getLastName())
                        .email(guest.getEmail())
                        .phoneNumber(guest.getPhoneNumber())
                        .build())
                .toList();

        return new ResponseEntity<>(guestDTO,HttpStatus.OK);
    }


    public ResponseEntity<GuestDTO> getById(Long guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GuestDTO guestDTO = GuestDTO.builder()
                .id(guest.get().getId())
                .firstName(guest.get().getFirstName())
                .lastName(guest.get().getLastName())
                .email(guest.get().getEmail())
                .phoneNumber(guest.get().getPhoneNumber())
                .build();

        return new ResponseEntity<>(guestDTO,HttpStatus.OK);
    }

    public ResponseEntity<GuestDTO> updateGuest(Long guestId,GuestDTO guestDTO) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        guest.get().setFirstName(guestDTO.getFirstName());
        guest.get().setLastName(guestDTO.getLastName());
        guest.get().setEmail(guestDTO.getEmail());
        guest.get().setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(guest.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
