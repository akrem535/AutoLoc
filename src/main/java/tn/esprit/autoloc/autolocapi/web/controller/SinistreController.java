package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Sinistre;
import tn.esprit.autoloc.autolocapi.service.ISinistreService;

import java.util.List;

@Tag(name = "Gestion des Sinistres")
@RestController
@RequestMapping("/sinistres")
@RequiredArgsConstructor
public class SinistreController {

    private final ISinistreService sinistreService;

    @Operation(description = "Récupérer tous les sinistres de la base de données")
    @GetMapping("/retrieve-all-sinistres")
    public List<Sinistre> getSinistres() {
        return sinistreService.findAll();
    }

    @Operation(description = "Récupérer un sinistre par son identifiant")
    @GetMapping("/retrieve-sinistre/{sinistre-id}")
    public Sinistre retrieveSinistre(@PathVariable("sinistre-id") Long sinistreId) {
        return sinistreService.findById(sinistreId);
    }

    @Operation(description = "Ajouter un nouveau sinistre")
    @PostMapping("/add-sinistre")
    public Sinistre addSinistre(@RequestBody Sinistre sinistre) {
        return sinistreService.create(sinistre);
    }

    @Operation(description = "Modifier un sinistre existant")
    @PutMapping("/modify-sinistre")
    public Sinistre modifySinistre(@RequestBody Sinistre sinistre) {
        return sinistreService.update(sinistre.getIdSinistre(), sinistre);
    }

    @Operation(description = "Supprimer un sinistre par son identifiant")
    @DeleteMapping("/remove-sinistre/{sinistre-id}")
    public void removeSinistre(@PathVariable("sinistre-id") Long sinistreId) {
        sinistreService.deleteById(sinistreId);
    }
}
