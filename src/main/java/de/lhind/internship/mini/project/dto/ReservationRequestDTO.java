package de.lhind.internship.mini.project.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReservationRequestDTO {

    private Long id;

    private Long guestId;

    private Long roomId;

    @FutureOrPresent(message = "Check-in date cannot be in the past")
    private LocalDate checkInDate;

    @FutureOrPresent(message = "Check-out date cannot be in the past")
    private LocalDate checkOutDate;

    @Min(1)
    private int numberOfGuests;



}
