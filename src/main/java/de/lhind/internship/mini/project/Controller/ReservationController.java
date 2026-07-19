package de.lhind.internship.mini.project.Controller;



import de.lhind.internship.mini.project.Enums.ReservationStatus;
import de.lhind.internship.mini.project.Service.ReservationService;
import de.lhind.internship.mini.project.dto.ReservationDTO;
import de.lhind.internship.mini.project.dto.ReservationRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @PutMapping
    public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationRequestDTO reservationDTO){
        return reservationService.createReservation(reservationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> findReservationByID(@PathVariable Long id){
        return reservationService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll(){
        return reservationService.findAll();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                                       @RequestParam ReservationStatus reservationStatus){
        return reservationService.updateStatus(id,reservationStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        return reservationService.delete(id);
    }









}
