package ma.enset.hopital.repositories;

import ma.enset.hopital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByNom(String nom);
}
