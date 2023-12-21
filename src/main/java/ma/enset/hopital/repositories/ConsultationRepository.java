package ma.enset.hopital.repositories;

import ma.enset.hopital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

}
