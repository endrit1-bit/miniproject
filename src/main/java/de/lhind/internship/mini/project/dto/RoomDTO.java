package de.lhind.internship.mini.project.dto;


import de.lhind.internship.mini.project.Entity.Hotel;
import de.lhind.internship.mini.project.Enums.RoomStatus;
import de.lhind.internship.mini.project.Enums.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {

    private Long id;

    @NotBlank
    private String roomNumber;

    private RoomType roomType;

    @Min(1)
    private int capacity;

    @NotNull
    private BigDecimal pricePerNight;
    private RoomStatus status;
    private Long hotelId;
}
