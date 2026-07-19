package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.Reservation;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("""
SELECT COUNT(r)
FROM Reservation r
WHERE r.room.id = :roomId
AND r.status <> 'CANCELLED'
AND r.checkInDate < :checkOutDate
AND r.checkOutDate > :checkInDate
""")
    long countOverlappingReservations(
            Long roomId,
            LocalDate checkInDate,
            LocalDate checkOutDate
    );

    List<Reservation> findByGuestId(Long guestId);

}
