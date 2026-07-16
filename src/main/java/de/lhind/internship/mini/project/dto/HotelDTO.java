package de.lhind.internship.mini.project.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HotelDTO {


    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotBlank
    private int starRating;
}
