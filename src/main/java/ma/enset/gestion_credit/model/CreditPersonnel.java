package ma.enset.gestion_credit.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PERSONNEL")
@Data @AllArgsConstructor @NoArgsConstructor
public class CreditPersonnel extends Credit{
    private String motif;
}
