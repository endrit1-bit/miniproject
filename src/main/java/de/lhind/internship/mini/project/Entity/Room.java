package de.lhind.internship.mini.project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column
    private String roomType;

    @Column
    private int capacity;

    @Column
    private double pricePerNight;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel",referencedColumnName = "id")
    private Hotel hotel;
}
