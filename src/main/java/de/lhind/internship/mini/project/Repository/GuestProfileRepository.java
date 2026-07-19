package de.lhind.internship.mini.project.Repository;

import de.lhind.internship.mini.project.Entity.GuestProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestProfileRepository extends JpaRepository<GuestProfile,Long> {
}
