package ma.enset.gestion_credit.service;

import lombok.AllArgsConstructor;
import ma.enset.gestion_credit.dto.*;
import ma.enset.gestion_credit.enums.Status;
import ma.enset.gestion_credit.mapper.dtoMapper;
import ma.enset.gestion_credit.model.*;
import ma.enset.gestion_credit.repository.ClientRepository;
import ma.enset.gestion_credit.repository.CreditRepository;
import ma.enset.gestion_credit.repository.RemboursementRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;
    private final dtoMapper mapper;

    // -------- Clients --------
    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.findAll().stream()
                .map(mapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return mapper.fromClient(client);
    }

    // -------- Crédits --------
    @Override
    public CreditDTO saveCredit(CreditDTO dto) {
        Credit savedCredit = null;

        if (dto instanceof CreditImmobilierDTO) {
            CreditImmobilier credit = mapper.fromCreditImmobilierDTO((CreditImmobilierDTO) dto);
            credit.setStatut(Status.EN_COURS);
            credit.setDateDemande(new Date());
            savedCredit = creditRepository.save(credit);
        } else if (dto instanceof CreditProfessionnelDTO) {
            CreditProfessionnel credit = mapper.fromCreditProfessionnelDTO((CreditProfessionnelDTO) dto);
            credit.setStatut(Status.EN_COURS);
            credit.setDateDemande(new Date());
            savedCredit = creditRepository.save(credit);
        } else if (dto instanceof CreditPersonnelDTO) {
            CreditPersonnel credit = mapper.fromCreditPersonnelDTO((CreditPersonnelDTO) dto);
            credit.setStatut(Status.EN_COURS);
            credit.setDateDemande(new Date());
            savedCredit = creditRepository.save(credit);
        }

        return mapper.fromCredit(savedCredit);
    }

    @Override
    public CreditDTO getCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit introuvable"));
        return mapper.fromCredit(credit);
    }

    @Override
    public List<CreditDTO> listCredits() {
        return creditRepository.findAll().stream()
                .map(mapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCredit(CreditDTO dto) {
        Credit existing = creditRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Credit updated = null;

        if (dto instanceof CreditImmobilierDTO) {
            CreditImmobilier credit = mapper.fromCreditImmobilierDTO((CreditImmobilierDTO) dto);
            credit.setDateDemande(existing.getDateDemande()); // conserver
            updated = creditRepository.save(credit);
        } else if (dto instanceof CreditProfessionnelDTO) {
            CreditProfessionnel credit = mapper.fromCreditProfessionnelDTO((CreditProfessionnelDTO) dto);
            credit.setDateDemande(existing.getDateDemande());
            updated = creditRepository.save(credit);
        } else if (dto instanceof CreditPersonnelDTO) {
            CreditPersonnel credit = mapper.fromCreditPersonnelDTO((CreditPersonnelDTO) dto);
            credit.setDateDemande(existing.getDateDemande());
            updated = creditRepository.save(credit);
        }

        return mapper.fromCredit(updated);
    }

    @Override
    public void deleteCredit(Long creditId) {
        if (!creditRepository.existsById(creditId)) {
            throw new RuntimeException("Crédit non trouvé");
        }
        creditRepository.deleteById(creditId);
    }

    // -------- Remboursements --------
    @Override
    public RemboursementDTO addRemboursement(Long creditId, RemboursementDTO dto) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit introuvable"));

        Remboursement remboursement = mapper.fromRemboursementDTO(dto);
        remboursement.setCredit(credit);
        remboursement.setDate(new Date());

        return mapper.fromRemboursement(remboursementRepository.save(remboursement));
    }

    @Override
    public List<RemboursementDTO> listRemboursements(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit introuvable"));

        return remboursementRepository.findByCredit(credit).stream()
                .map(mapper::fromRemboursement)
                .collect(Collectors.toList());
    }
}
