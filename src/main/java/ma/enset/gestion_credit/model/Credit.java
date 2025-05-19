package ma.enset.gestion_credit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.gestion_credit.enums.Status;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 20, discriminatorType = DiscriminatorType.STRING)
public abstract class Credit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDemande;

    @Enumerated(EnumType.STRING)
    private Status statut;

    private Date dateAcceptation;

    private Double montant;
    private Integer duree; // durée en mois
    private Double tauxInteret;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Remboursement> remboursements;
}
