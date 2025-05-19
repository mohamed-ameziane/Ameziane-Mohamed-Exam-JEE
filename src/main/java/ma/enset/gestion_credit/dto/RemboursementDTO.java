package ma.enset.gestion_credit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RemboursementDTO {
    private Long id;
    private Double montant;
    private Date date;
}

