package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

import java.util.List;

@Tag(name = "Gestion des Agences")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    // http://localhost:8082/api/agences/retrieve-all-agences
    @Operation(description = "Récupérer toutes les agences de la base de données")
    @GetMapping("/retrieve-all-agences")
    public List<Agence> getAgences() {
        return agenceService.findAll();
    }

    // http://localhost:8082/api/agences/retrieve-agence/1
    @Operation(description = "Récupérer une agence par son identifiant")
    @GetMapping("/retrieve-agence/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceService.findById(agenceId);
    }

    // http://localhost:8082/api/agences/add-agence
    @Operation(description = "Ajouter une nouvelle agence")
    @PostMapping("/add-agence")
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    // http://localhost:8082/api/agences/modify-agence
    @Operation(description = "Modifier une agence existante")
    @PutMapping("/modify-agence")
    public Agence modifyAgence(@RequestBody Agence agence) {
        return agenceService.update(agence.getIdAgence(), agence);
    }

    // http://localhost:8082/api/agences/remove-agence/{agence-id}
    @Operation(description = "Supprimer une agence par son identifiant")
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.deleteById(agenceId);
    }
}
