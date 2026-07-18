package de.lhind.internship.mini.project.Service;

import ch.qos.logback.core.model.InsertFromJNDIModel;
import de.lhind.internship.mini.project.Entity.Hotel;
import de.lhind.internship.mini.project.Entity.Room;
import de.lhind.internship.mini.project.Enums.RoomStatus;
import de.lhind.internship.mini.project.Repository.HotelRepository;
import de.lhind.internship.mini.project.Repository.RoomRepository;
import de.lhind.internship.mini.project.dto.RoomDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class RoomService {

    private HotelRepository hotelRepository;
    private HotelService hotelService;
    private RoomRepository roomRepository;


    public ResponseEntity<RoomDTO> save(Long hotelId, @Valid RoomDTO roomDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Room room = new Room();


        room.setHotel(hotel.get());
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setCapacity(roomDTO.getCapacity());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setStatus(roomDTO.getStatus());


        Room savedRoom = roomRepository.save(room);

        roomDTO.setId(savedRoom.getId());
        roomDTO.setHotelId(savedRoom.getHotel().getId());
        roomDTO.setRoomType(savedRoom.getRoomType());
        roomDTO.setRoomNumber(savedRoom.getRoomNumber());
        roomDTO.setCapacity(savedRoom.getCapacity());
        roomDTO.setPricePerNight(savedRoom.getPricePerNight());
        roomDTO.setStatus(savedRoom.getStatus());

        return new ResponseEntity<>(roomDTO,HttpStatus.CREATED);

    }


    public ResponseEntity<List<RoomDTO>> getAllRooms(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Room> rooms = hotel.get().getRooms();
        List<RoomDTO> foundRooms = rooms.stream()
                .map(room -> RoomDTO.builder()
                        .hotelId(hotel.get().getId())
                        .id(room.getId())
                        .roomNumber(room.getRoomNumber())
                        .roomType(room.getRoomType())
                        .capacity(room.getCapacity())
                        .pricePerNight(room.getPricePerNight())
                        .status(room.getStatus())
                        .build())
                .toList();

        return new ResponseEntity<>(foundRooms,HttpStatus.OK);
    }

    public ResponseEntity<RoomDTO> getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RoomDTO roomDTO = RoomDTO.builder()
                .hotelId(room.get().getHotel().getId())
                .id(room.get().getId())
                .roomType(room.get().getRoomType())
                .roomNumber(room.get().getRoomNumber())
                .capacity(room.get().getCapacity())
                .status(room.get().getStatus())
                .pricePerNight(room.get().getPricePerNight()).build();

        return new ResponseEntity<>(roomDTO,HttpStatus.OK);
    }


    public ResponseEntity<Void> updateRoom(Long id, @Valid RoomDTO roomDTO) {
        Optional<Room> room = roomRepository.findById(id);
        Optional<Hotel> hotel = hotelRepository.findById(roomDTO.getHotelId());
        if (room.isEmpty() || hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        room.get().setRoomNumber(roomDTO.getRoomNumber());
        room.get().setRoomType(roomDTO.getRoomType());
        room.get().setCapacity(roomDTO.getCapacity());
        room.get().setStatus(roomDTO.getStatus());
        room.get().setPricePerNight(roomDTO.getPricePerNight());
        roomRepository.save(room.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        roomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<RoomStatus> updateStatus(Long id,RoomStatus roomStatus) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        room.get().setStatus(roomStatus);
        roomRepository.save(room.get());
        return new ResponseEntity<>(room.get().getStatus(),HttpStatus.OK);
    }
}
