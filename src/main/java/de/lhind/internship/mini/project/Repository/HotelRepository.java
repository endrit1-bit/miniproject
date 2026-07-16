package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
