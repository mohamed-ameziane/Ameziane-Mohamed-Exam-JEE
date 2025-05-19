package ma.enset.gestion_credit.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.gestion_credit.enums.TypeBien;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data @AllArgsConstructor @NoArgsConstructor
public class CreditImmobilier extends Credit{
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;
}
