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

    public ResponseEntity<HotelDTO> save(HotelDTO hotelDTO) {

        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());
        Hotel savedHotel = hotelRepository.save(hotel);

        hotelDTO.setId(savedHotel.getId());
        hotelDTO.setName(savedHotel.getName());
        hotelDTO.setCity(savedHotel.getCity());
        hotel.setAddress(hotel.getAddress());
        hotel.setStarRating(savedHotel.getStarRating());

       return new ResponseEntity<>(hotelDTO,HttpStatus.CREATED);
    }


    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();

        List<HotelDTO> hotelDTO = hotels.stream()
                .map(hotel1 -> HotelDTO.builder()
                        .id(hotel1.getId())
                        .name(hotel1.getName())
                        .address(hotel1.getAddress())
                        .city(hotel1.getCity())
                        .starRating(hotel1.getStarRating())
                        .build())
                .toList();

        return new ResponseEntity<>(hotelDTO,HttpStatus.OK);
    }

    public ResponseEntity<HotelDTO> getHotelById(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HotelDTO hotelDTO = HotelDTO.builder()
                .id(hotel.get().getId())
                .name(hotel.get().getName())
                .address(hotel.get().getAddress())
                .city(hotel.get().getCity())
                .starRating(hotel.get().getStarRating())
                .build();


        return new ResponseEntity<>(hotelDTO,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateHotel(Long hotelId,HotelDTO hotelDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        hotel.get().setName(hotelDTO.getName());
        hotel.get().setCity(hotelDTO.getCity());
        hotel.get().setAddress(hotelDTO.getAddress());
        hotel.get().setStarRating(hotelDTO.getStarRating());

        hotelRepository.save(hotel.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<Void> deleteHotel(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hotelRepository.deleteById(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

