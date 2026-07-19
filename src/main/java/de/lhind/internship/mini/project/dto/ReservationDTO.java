package de.lhind.internship.mini.project.dto;

import de.lhind.internship.mini.project.Enums.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDTO {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private Double totalPrice;
    private ReservationStatus status;
    private LocalDate createdAt;
    private Long guestId;
    private Long roomId;

}
