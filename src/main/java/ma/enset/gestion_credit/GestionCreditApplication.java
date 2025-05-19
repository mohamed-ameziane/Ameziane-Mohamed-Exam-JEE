package ma.enset.gestion_credit;

import ma.enset.gestion_credit.enums.Status;
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
                // Step 1: Create Clients
                Stream.of("Ali", "Fatima", "Omar").forEach(name -> {
                    Client client = new Client();
                    client.setNom(name);
                    client.setEmail(name.toLowerCase() + "@gmail.com");
                    clientRepository.save(client);
                });

                // Step 2: Create Credits for each client
                Random random = new Random();
                List<Client> clients = clientRepository.findAll();

                clients.forEach(client -> {
                    // Credit Immobilier
                    CreditImmobilier credit1 = new CreditImmobilier();
                    credit1.setDateDemande(new Date());
                    credit1.setStatut(Status.EN_COURS);
                    credit1.setDateAcceptation(null);
                    credit1.setMontant(500000.0 + random.nextInt(100000));
                    credit1.setDuree(240); // 20 years
                    credit1.setTauxInteret(4.5);
                    credit1.setClient(client);
                    creditRepository.save(credit1);

                    // Credit Professionnel
                    CreditProfessionnel credit2 = new CreditProfessionnel();
                    credit2.setDateDemande(new Date());
                    credit2.setStatut(Status.EN_COURS);
                    credit2.setDateAcceptation(null);
                    credit2.setMontant(300000.0 + random.nextInt(50000));
                    credit2.setDuree(120); // 10 years
                    credit2.setTauxInteret(5.2);
                    credit2.setClient(client);
                    creditRepository.save(credit2);

                    // Credit Personnel
                    CreditPersonnel credit3 = new CreditPersonnel();
                    credit3.setDateDemande(new Date());
                    credit3.setStatut(Status.ACCEPTE);
                    credit3.setDateAcceptation(new Date());
                    credit3.setMontant(80000.0 + random.nextInt(20000));
                    credit3.setDuree(48); // 4 years
                    credit3.setTauxInteret(6.0);
                    credit3.setClient(client);
                    creditRepository.save(credit3);

                    // Step 3: Add Remboursements for credit3 (personnel)
                    for (int i = 0; i < 5; i++) {
                        Remboursement r = new Remboursement();
                        r.setCredit(credit3);
                        r.setMontant(2000.0 + random.nextInt(1000));
                        r.setDate(new Date());
                        remboursementRepository.save(r);
                    }
                });
            };
        }
}
