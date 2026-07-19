package de.lhind.internship.mini.project.Service;


import de.lhind.internship.mini.project.Entity.Guest;
import de.lhind.internship.mini.project.Entity.Reservation;
import de.lhind.internship.mini.project.Entity.Room;
import de.lhind.internship.mini.project.Enums.ReservationStatus;
import de.lhind.internship.mini.project.Enums.RoomStatus;
import de.lhind.internship.mini.project.Repository.GuestRepository;
import de.lhind.internship.mini.project.Repository.ReservationRepository;
import de.lhind.internship.mini.project.Repository.RoomRepository;
import de.lhind.internship.mini.project.dto.ReservationDTO;
import de.lhind.internship.mini.project.dto.ReservationRequestDTO;
import de.lhind.internship.mini.project.exception.RoomCapacityExeededException;
import de.lhind.internship.mini.project.exception.RoomNotAvailableException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReservationService {

    private ReservationRepository reservationRepository;
    private GuestRepository guestRepository;
    private RoomRepository roomRepository;

    public ResponseEntity<ReservationDTO> createReservation(ReservationRequestDTO request) {

        Optional<Guest> guest = guestRepository.findById(request.getGuestId());
        if (guest.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Room> room = roomRepository.findById(request.getRoomId());
        if (room.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (room.get().getStatus() == RoomStatus.MAINTENANCE){
            throw new RoomNotAvailableException("Room with ID "
                    + request.getRoomId() + " is not available for the selected dates");
        }

        if (room.get().getCapacity() < request.getNumberOfGuests()){
            throw new RoomCapacityExeededException("Room with ID " + request.getRoomId() + " has a capacity of " +
                    room.get().getCapacity()
                    + " but " + request.getNumberOfGuests() + " guests were requested");
        }

        long overlapCount = reservationRepository.countOverlappingReservations(request.getRoomId(),request.getCheckInDate()
                ,request.getCheckOutDate());
        if (overlapCount > 0){
            throw new RoomNotAvailableException("Room with ID "
                    + request.getRoomId() + " is not available for the selected dates");
        }


        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(),request.getCheckOutDate());
        double totalPrice = room.get().getPricePerNight().doubleValue() * nights;


        Reservation reservation = new Reservation();
        reservation.setRoom(room.get());
        reservation.setGuest(guest.get());
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNumberOfGuests(request.getNumberOfGuests());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setTotalPrice(totalPrice);
        reservation.setCreatedAt(LocalDate.now());

        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(savedReservation.getId())
                .guestId(savedReservation.getId())
                .roomId(savedReservation.getId())
                .checkInDate(savedReservation.getCheckInDate())
                .checkOutDate(savedReservation.getCheckOutDate())
                .numberOfGuests(savedReservation.getNumberOfGuests())
                .status(savedReservation.getStatus())
                .totalPrice(savedReservation.getTotalPrice())
                .createdAt(savedReservation.getCreatedAt())
                .build();

        return new ResponseEntity<>(reservationDTO,HttpStatus.CREATED);
    }

    public ResponseEntity<List<ReservationDTO>> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationDTO> reservationDTOS = reservations.stream()
                .map(reservation -> ReservationDTO.builder()
                        .id(reservation.getId())
                        .guestId(reservation.getGuest().getId())
                        .roomId(reservation.getId())
                        .checkInDate(reservation.getCheckInDate())
                        .checkOutDate(reservation.getCheckOutDate())
                        .createdAt(reservation.getCreatedAt())
                        .totalPrice(reservation.getTotalPrice())
                        .status(reservation.getStatus())
                        .numberOfGuests(reservation.getNumberOfGuests())
                        .build())
                .toList();

        return new ResponseEntity<>(reservationDTOS,HttpStatus.OK);
    }

    public ResponseEntity<ReservationDTO> findById(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(reservation.get().getId())
                .roomId(reservation.get().getId())
                .guestId(reservation.get().getId())
                .checkInDate(reservation.get().getCheckInDate())
                .checkOutDate(reservation.get().getCheckOutDate())
                .createdAt(reservation.get().getCreatedAt())
                .totalPrice(reservation.get().getTotalPrice())
                .status(reservation.get().getStatus())
                .numberOfGuests(reservation.get().getNumberOfGuests())
                .build();


        return new ResponseEntity<>(reservationDTO,HttpStatus.OK);

    }

    public ResponseEntity<Void> updateStatus(Long id, ReservationStatus reservationStatus) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservation.get().setStatus(reservationStatus);
        reservationRepository.save(reservation.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
