package ma.enset.gestion_credit.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ma.enset.gestion_credit.dto.ClientDTO;
import ma.enset.gestion_credit.dto.CreditDTO;
import ma.enset.gestion_credit.dto.RemboursementDTO;
import ma.enset.gestion_credit.service.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
public class CreditRestController {

    private final CreditService creditService;

    // ----------- Crédits -----------
    @GetMapping
    public List<CreditDTO> listCredits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    public CreditDTO getCredit(@PathVariable Long id) {
        return creditService.getCredit(id);
    }

    @PostMapping
    public CreditDTO saveCredit(@RequestBody CreditDTO creditDTO) {
        return creditService.saveCredit(creditDTO);
    }

    @PutMapping("/{id}")
    public CreditDTO updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        creditDTO.setId(id);
        return creditService.updateCredit(creditDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }

    // ----------- Clients -----------
    @GetMapping("/clients")
    public List<ClientDTO> listClients() {
        return creditService.listClients();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return creditService.getClientById(id);
    }

    // ----------- Remboursements -----------
    @PostMapping("/{id}/remboursements")
    public RemboursementDTO addRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO dto) {
        return creditService.addRemboursement(id, dto);
    }

    @GetMapping("/{id}/remboursements")
    public List<RemboursementDTO> getRemboursements(@PathVariable Long id) {
        return creditService.listRemboursements(id);
    }
}