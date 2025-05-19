package ma.enset.gestion_credit.dto;

import lombok.Data;
import ma.enset.gestion_credit.enums.Status;

import java.util.Date;

@Data
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private Status statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;
    private ClientDTO client;
}
