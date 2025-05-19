package ma.enset.gestion_credit.mapper;

import ma.enset.gestion_credit.dto.*;
import ma.enset.gestion_credit.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class dtoMapper {

    // ----------- Client -------------
    public ClientDTO fromClient(Client client) {
        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);
        return dto;
    }

    public Client fromClientDTO(ClientDTO dto) {
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        return client;
    }

    // ----------- Remboursement -------------
    public RemboursementDTO fromRemboursement(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, dto);
        return dto;
    }

    public Remboursement fromRemboursementDTO(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(dto, remboursement);
        return remboursement;
    }

    // ----------- Crédit Immobilier -------------
    public CreditImmobilierDTO fromCreditImmobilier(CreditImmobilier credit) {
        CreditImmobilierDTO dto = new CreditImmobilierDTO();
        BeanUtils.copyProperties(credit, dto);
        dto.setClient(fromClient(credit.getClient()));
        dto.setTypeBien(credit.getTypeBien());
        return dto;
    }

    public CreditImmobilier fromCreditImmobilierDTO(CreditImmobilierDTO dto) {
        CreditImmobilier credit = new CreditImmobilier();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(fromClientDTO(dto.getClient()));
        return credit;
    }

    // ----------- Crédit Professionnel -------------
    public CreditProfessionnelDTO fromCreditProfessionnel(CreditProfessionnel credit) {
        CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
        BeanUtils.copyProperties(credit, dto);
        dto.setClient(fromClient(credit.getClient()));
        dto.setMotif(credit.getMotif());
        dto.setRaisonSociale(credit.getRaisonSociale());
        return dto;
    }

    public CreditProfessionnel fromCreditProfessionnelDTO(CreditProfessionnelDTO dto) {
        CreditProfessionnel credit = new CreditProfessionnel();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(fromClientDTO(dto.getClient()));
        return credit;
    }

    // ----------- Crédit Personnel -------------
    public CreditPersonnelDTO fromCreditPersonnel(CreditPersonnel credit) {
        CreditPersonnelDTO dto = new CreditPersonnelDTO();
        BeanUtils.copyProperties(credit, dto);
        dto.setClient(fromClient(credit.getClient()));
        dto.setMotif(credit.getMotif());
        return dto;
    }

    public CreditPersonnel fromCreditPersonnelDTO(CreditPersonnelDTO dto) {
        CreditPersonnel credit = new CreditPersonnel();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(fromClientDTO(dto.getClient()));
        return credit;
    }

    // ----------- Crédit générique -------------
    public CreditDTO fromCredit(Credit credit) {
        if (credit instanceof CreditImmobilier)
            return fromCreditImmobilier((CreditImmobilier) credit);
        else if (credit instanceof CreditProfessionnel)
            return fromCreditProfessionnel((CreditProfessionnel) credit);
        else if (credit instanceof CreditPersonnel)
            return fromCreditPersonnel((CreditPersonnel) credit);
        else return null;
    }
}