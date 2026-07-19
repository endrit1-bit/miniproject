package de.lhind.internship.mini.project.Controller;


import de.lhind.internship.mini.project.Service.HotelService;
import de.lhind.internship.mini.project.dto.HotelDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/hotels")
@RestController
@AllArgsConstructor
public class HotelController {

    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO){
        return hotelService.save(hotelDTO);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHotel(@PathVariable("id") Long hotelId,@Valid @RequestBody HotelDTO hotelDTO){
        return hotelService.updateHotel(hotelId,hotelDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") Long hotelId){
        return hotelService.deleteHotel(hotelId);
    }




}
