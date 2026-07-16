package de.lhind.internship.mini.project.Service;


import de.lhind.internship.mini.project.Entity.Hotel;
import de.lhind.internship.mini.project.Entity.Room;
import de.lhind.internship.mini.project.Repository.HotelRepository;
import de.lhind.internship.mini.project.Repository.RoomRepository;
import de.lhind.internship.mini.project.dto.HotelDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    public Hotel save(HotelDTO hotelDTO) {

        Hotel hotel = new Hotel();

        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());

        return hotelRepository.save(hotel);
    }


    public ResponseEntity<List<HotelDTO>> getAllHotels(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Hotel> hotels = hotelRepository.findById(hotelId);

        List<HotelDTO> hotelDTO = hotels.stream()
                .map(hotel1 -> HotelDTO.builder()
                        .name(hotel1.getName())
                        .address(hotel1.getAddress())
                        .city(hotel1.getCity())
                        .starRating(hotel1.getStarRating())
                        .build())
                .toList();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<HotelDTO>> getHotelById(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Hotel> hotels = hotelRepository.findById(hotelId);
        List<HotelDTO> hotelDTOS = hotels.stream()
                .map(hotel1 -> HotelDTO.builder()
                        .name(hotel1.getName())
                        .city(hotel1.getCity())
                        .address(hotel1.getAddress())
                        .starRating(hotel1.getStarRating())
                        .build())
                .toList();

        return new ResponseEntity<>(hotelDTOS,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateHotel(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HotelDTO hotelDTO = new HotelDTO();

        hotel.get().setName(hotelDTO.getName());
        hotel.get().setCity(hotelDTO.getCity());
        hotel.get().setAddress(hotelDTO.getAddress());
        hotel.get().setStarRating(hotelDTO.getStarRating());

        hotelRepository.save(hotel.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

