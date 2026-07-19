package de.lhind.internship.mini.project.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDTO {


        private Long id;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @NotBlank
        @Email(message = "Email must be a valid email address")
        private String email;

        @NotBlank
        private String phoneNumber;

        @Valid
        private GuestProfileDTO guestProfile;

        @Valid
        private List<ReservationRequestDTO> reservations = new ArrayList<>();

    }

