package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@Tag(name = "Gestion des Contrats")
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @Operation(description = "Récupérer tous les contrats de la base de données")
    @GetMapping("/retrieve-all-contrats")
    public List<Contrat> getContrats() {
        return contratService.findAll();
    }

    @Operation(description = "Récupérer un contrat par son identifiant")
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public Contrat retrieveContrat(@PathVariable("contrat-id") Long contratId) {
        return contratService.findById(contratId);
    }

    @Operation(description = "Ajouter un nouveau contrat")
    @PostMapping("/add-contrat")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    @Operation(description = "Modifier un contrat existant")
    @PutMapping("/modify-contrat")
    public Contrat modifyContrat(@RequestBody Contrat contrat) {
        return contratService.update(contrat.getIdContrat(), contrat);
    }

    @Operation(description = "Supprimer un contrat par son identifiant")
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContrat(@PathVariable("contrat-id") Long contratId) {
        contratService.deleteById(contratId);
    }
}
