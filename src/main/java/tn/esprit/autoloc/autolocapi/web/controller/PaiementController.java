package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@Tag(name = "Gestion des Paiements")
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    // API en lecture seule — pas de POST, PUT, DELETE

    @Operation(description = "Récupérer tous les paiements de la base de données")
    @GetMapping("/retrieve-all-paiements")
    public List<Paiement> getPaiements() {
        return paiementService.findAll();
    }

    @Operation(description = "Récupérer un paiement par son identifiant")
    @GetMapping("/retrieve-paiement/{paiement-id}")
    public Paiement retrievePaiement(@PathVariable("paiement-id") Long paiementId) {
        return paiementService.findById(paiementId);
    }
}
