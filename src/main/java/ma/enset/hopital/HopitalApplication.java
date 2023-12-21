package ma.enset.hopital;

import ma.enset.hopital.entities.*;
import ma.enset.hopital.repositories.ConsultationRepository;
import ma.enset.hopital.repositories.MedecinRepository;
import ma.enset.hopital.repositories.PatientRepository;
import ma.enset.hopital.repositories.RendezVousRepository;
import ma.enset.hopital.service.HopitalServiceImpl;
import ma.enset.hopital.service.IHopitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HopitalApplication {

    public static void main(String[] args) {

        SpringApplication.run(HopitalApplication.class, args);
    }
    @Bean
    CommandLineRunner start(HopitalServiceImpl hopitalService,PatientRepository patientRepository,MedecinRepository medecinRepository,RendezVousRepository rendezVousRepository){
        return args -> {
            Stream.of("anass","khalid","imad").forEach(
                        name->{
                            Patient p=new Patient();
                            p.setNom(name);
                            p.setDateNaissance(new Date());
                            p.setMalade(false);
                            hopitalService.savePatient(p);
                        }
                );

            Stream.of("mohamed","kawtar","lamyae").forEach(
                        name->{
                            Medecin m=new Medecin();
                            m.setNom(name);
                            m.setMail(name+"@gmail.com");
                            m.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                            hopitalService.saveMedecin(m);
                        }
                );

            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1=patientRepository.findByNom("anass");

            Medecin medecin=medecinRepository.findByNom("kawtar");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(statusRDV.DONE);
            rendezVous.setPatient(patient1);
            rendezVous.setMedecin(medecin);
            hopitalService.saveRendezVous(rendezVous);


            RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRapport("la personne est malade");
            consultation.setRendezVous(rendezVous1);
            hopitalService.saveConsultation(consultation);


        };
    }
}
