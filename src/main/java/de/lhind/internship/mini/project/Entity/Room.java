package de.lhind.internship.mini.project.Entity;

import de.lhind.internship.mini.project.Enums.RoomStatus;
import de.lhind.internship.mini.project.Enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomNumber")
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "roomType")
    private RoomType roomType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "pricePerNight")
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel",referencedColumnName = "id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reservation> reservations = new ArrayList<>();
}
