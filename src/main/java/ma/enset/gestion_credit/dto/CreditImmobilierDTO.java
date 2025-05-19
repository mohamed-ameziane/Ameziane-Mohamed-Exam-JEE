package ma.enset.gestion_credit.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.gestion_credit.enums.TypeBien;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien;
}