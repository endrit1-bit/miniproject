package de.lhind.internship.mini.project.Controller;


import de.lhind.internship.mini.project.Enums.RoomStatus;
import de.lhind.internship.mini.project.Service.HotelService;
import de.lhind.internship.mini.project.Service.RoomService;
import de.lhind.internship.mini.project.dto.RoomDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class RoomController {

    private HotelService hotelService;
    private RoomService roomService;

    @PostMapping("/api/hotels/{hotelId}/rooms")
    public ResponseEntity<RoomDTO> createRoom(@PathVariable Long hotelId, @Valid @RequestBody RoomDTO roomDTO){
        return roomService.save(hotelId,roomDTO);
    }

    @GetMapping("/api/hotels/{hotelId}/rooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms(@PathVariable Long hotelId){
        return roomService.getAllRooms(hotelId);
    }

    @GetMapping("/api/rooms/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id,@Valid @RequestBody RoomDTO roomDTO){
        return roomService.getRoomById(id);
    }

    @PutMapping("/api/rooms/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDTO roomDTO){
        return roomService.updateRoom(id,roomDTO);
    }

    @PatchMapping("/api/rooms/{id}/status")
    public ResponseEntity<RoomStatus> roomStatus(@PathVariable Long id, @Valid @RequestParam RoomStatus roomStatus){
        return roomService.updateStatus(id,roomStatus);
    }


    @DeleteMapping("/api/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        return roomService.deleteRoom(id);
    }




}
