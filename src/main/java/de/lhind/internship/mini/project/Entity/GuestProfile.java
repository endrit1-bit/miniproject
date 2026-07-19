package de.lhind.internship.mini.project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "guest_profile")
public class GuestProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private LocalDate dateOfBirth;

    private String nationality;
    private String preferredLanguage;

    @OneToOne(mappedBy = "guestProfile")
    private Guest guest;



}
