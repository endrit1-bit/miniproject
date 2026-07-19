package de.lhind.internship.mini.project.Repository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomReservationCount {
    @Query(
            value = """
        SELECT r.room_id AS roomId, COUNT(*) AS reservationCount
        FROM reservation r
        GROUP BY r.room_id
        ORDER BY reservationCount DESC
        LIMIT 5
        """,
            nativeQuery = true
    )
    List<RoomReservationCount> findTop5RoomsByReservationCount();
}
