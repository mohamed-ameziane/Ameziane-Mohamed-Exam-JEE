package ma.enset.gestion_credit.service;

import ma.enset.gestion_credit.dto.ClientDTO;
import ma.enset.gestion_credit.dto.CreditDTO;
import ma.enset.gestion_credit.dto.RemboursementDTO;

import java.util.List;

public interface CreditService {
    // Client
    List<ClientDTO> listClients();
    ClientDTO getClientById(Long clientId);

    // Crédit
    CreditDTO saveCredit(CreditDTO creditDTO); // générique
    CreditDTO getCredit(Long creditId);
    List<CreditDTO> listCredits();
    CreditDTO updateCredit(CreditDTO creditDTO);
    void deleteCredit(Long creditId);

    // Remboursements
    RemboursementDTO addRemboursement(Long creditId, RemboursementDTO dto);
    List<RemboursementDTO> listRemboursements(Long creditId);
}


