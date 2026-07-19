package de.lhind.internship.mini.project.dto;

import de.lhind.internship.mini.project.Entity.Guest;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestProfileDTO {

    private String address;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private String nationality;

    private String preferredLanguage;

    private Guest guest;

    private Long id;

}
