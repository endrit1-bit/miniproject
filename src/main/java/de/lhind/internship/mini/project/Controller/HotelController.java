package de.lhind.internship.mini.project.Controller;


import de.lhind.internship.mini.project.Entity.Hotel;
import de.lhind.internship.mini.project.Service.HotelService;
import de.lhind.internship.mini.project.dto.HotelDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/hotels")
@RestController
@AllArgsConstructor
public class HotelController {

    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Void> createHotel(@Valid @RequestBody HotelDTO hotelDTO){
        hotelService.save(hotelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable("Id") Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHotel(@PathVariable("Id") Long hotelId,@Valid @RequestBody HotelDTO hotelDTO){
        return hotelService.updateHotel(hotelId,hotelDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("Id") Long hotelId){
        return hotelService.deleteHotel(hotelId);
    }




}
