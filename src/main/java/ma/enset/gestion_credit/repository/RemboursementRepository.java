package ma.enset.gestion_credit.repository;

import ma.enset.gestion_credit.model.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
