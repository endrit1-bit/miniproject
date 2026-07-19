package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.Room;
import de.lhind.internship.mini.project.Enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

    public interface RoomRepository extends JpaRepository<Room,Long>{
    List<Room> findByHotelIdAndStatus(Long hotelId, RoomStatus status);

}
