package de.lhind.internship.mini.project.Entity;

import de.lhind.internship.mini.project.Enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfGuests;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @CreatedDate
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest",referencedColumnName = "id")
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room",referencedColumnName = "id")
    private Room room;
}
