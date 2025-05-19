package ma.enset.gestion_credit.repository;

import ma.enset.gestion_credit.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
