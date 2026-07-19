package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.Guest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long> {
    boolean existsByEmail(String email);

}
