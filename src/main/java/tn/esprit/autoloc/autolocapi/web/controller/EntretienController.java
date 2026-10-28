package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Entretien;
import tn.esprit.autoloc.autolocapi.service.IEntretienService;

import java.util.List;

@Tag(name = "Gestion des Entretiens")
@RestController
@RequestMapping("/entretiens")
@RequiredArgsConstructor
public class EntretienController {

    private final IEntretienService entretienService;

    @Operation(description = "Récupérer tous les entretiens de la base de données")
    @GetMapping("/retrieve-all-entretiens")
    public List<Entretien> getEntretiens() {
        return entretienService.findAll();
    }

    @Operation(description = "Récupérer un entretien par son identifiant")
    @GetMapping("/retrieve-entretien/{entretien-id}")
    public Entretien retrieveEntretien(@PathVariable("entretien-id") Long entretienId) {
        return entretienService.findById(entretienId);
    }

    @Operation(description = "Ajouter un nouvel entretien")
    @PostMapping("/add-entretien")
    public Entretien addEntretien(@RequestBody Entretien entretien) {
        return entretienService.create(entretien);
    }

    @Operation(description = "Modifier un entretien existant")
    @PutMapping("/modify-entretien")
    public Entretien modifyEntretien(@RequestBody Entretien entretien) {
        return entretienService.update(entretien.getIdEntretien(), entretien);
    }

    @Operation(description = "Supprimer un entretien par son identifiant")
    @DeleteMapping("/remove-entretien/{entretien-id}")
    public void removeEntretien(@PathVariable("entretien-id") Long entretienId) {
        entretienService.deleteById(entretienId);
    }
}
