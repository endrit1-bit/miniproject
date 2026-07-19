package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    List<Hotel>findByCityIgnoreCase(String city);

}
