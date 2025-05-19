package ma.enset.gestion_credit;

import ma.enset.gestion_credit.enums.Status;
import ma.enset.gestion_credit.enums.TypeBien;
import ma.enset.gestion_credit.enums.TypeRemboursement;
import ma.enset.gestion_credit.model.*;
import ma.enset.gestion_credit.repository.ClientRepository;
import ma.enset.gestion_credit.repository.CreditRepository;
import ma.enset.gestion_credit.repository.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionCreditApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCreditApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {
            // Créer des clients
            Stream.of("Ali", "Fatima", "Omar").forEach(name -> {
                Client client = new Client();
                client.setNom(name);
                client.setEmail(name.toLowerCase() + "@gmail.com");
                clientRepository.save(client);
            });

            Random random = new Random();
            List<Client> clients = clientRepository.findAll();

            for (Client client : clients) {
                // Crédit Immobilier
                CreditImmobilier creditImmobilier = new CreditImmobilier();
                creditImmobilier.setDateDemande(new Date());
                creditImmobilier.setStatut(Status.EN_COURS);
                creditImmobilier.setDateAcceptation(null);
                creditImmobilier.setMontant(500000.0 + random.nextInt(100000));
                creditImmobilier.setDuree(240);
                creditImmobilier.setTauxInteret(4.5);
                creditImmobilier.setTypeBien(TypeBien.APPARTEMENT);
                creditImmobilier.setClient(client);
                creditRepository.save(creditImmobilier);

                // Crédit Professionnel
                CreditProfessionnel creditPro = new CreditProfessionnel();
                creditPro.setDateDemande(new Date());
                creditPro.setStatut(Status.EN_COURS);
                creditPro.setDateAcceptation(null);
                creditPro.setMontant(300000.0 + random.nextInt(50000));
                creditPro.setDuree(120);
                creditPro.setTauxInteret(5.2);
                creditPro.setMotif("test Motif");
                creditPro.setRaisonSociale("test Raison Sociale");
                creditPro.setClient(client);
                creditRepository.save(creditPro);

                // Crédit Personnel
                CreditPersonnel creditPerso = new CreditPersonnel();
                creditPerso.setDateDemande(new Date());
                creditPerso.setStatut(Status.ACCEPTE);
                creditPerso.setDateAcceptation(new Date());
                creditPerso.setMontant(80000.0 + random.nextInt(20000));
                creditPerso.setDuree(48);
                creditPerso.setTauxInteret(6.0);
                creditPerso.setMotif("Achat véhicule");
                creditPerso.setClient(client);
                creditRepository.save(creditPerso);

                // Remboursements pour le crédit personnel
                for (int i = 0; i < 5; i++) {
                    Remboursement remboursement = new Remboursement();
                    remboursement.setCredit(creditPerso);
                    remboursement.setMontant(2000.0 + random.nextInt(1000));
                    remboursement.setDate(new Date());
                    remboursement.setType(Math.random()>0.5?TypeRemboursement.MENSUALITE:TypeRemboursement.REMBOURSEMENT_ANTICIPE);
                    remboursementRepository.save(remboursement);
                }
            }
        };
    }

}
