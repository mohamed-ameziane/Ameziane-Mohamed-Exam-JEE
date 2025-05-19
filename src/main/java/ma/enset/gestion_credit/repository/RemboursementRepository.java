package ma.enset.gestion_credit.repository;

import ma.enset.gestion_credit.dto.RemboursementDTO;
import ma.enset.gestion_credit.model.Credit;
import ma.enset.gestion_credit.model.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCredit(Credit credit);
}
