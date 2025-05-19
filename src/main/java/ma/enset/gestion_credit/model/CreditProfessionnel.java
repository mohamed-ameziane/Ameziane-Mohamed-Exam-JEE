package ma.enset.gestion_credit.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data @AllArgsConstructor @NoArgsConstructor
public class CreditProfessionnel extends Credit{
    private String motif;
    private String raisonSociale;
}

