package ma.enset.gestion_credit.repository;

import ma.enset.gestion_credit.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {

}
