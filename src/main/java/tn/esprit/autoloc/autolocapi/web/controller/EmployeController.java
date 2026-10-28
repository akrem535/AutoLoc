package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@Tag(name = "Gestion des Employés")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @Operation(description = "Récupérer tous les employés de la base de données")
    @GetMapping("/retrieve-all-employes")
    public List<Employe> getEmployes() {
        return employeService.findAll();
    }

    @Operation(description = "Récupérer un employé par son identifiant")
    @GetMapping("/retrieve-employe/{employe-id}")
    public Employe retrieveEmploye(@PathVariable("employe-id") Long employeId) {
        return employeService.findById(employeId);
    }

    @Operation(description = "Ajouter un nouvel employé")
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @Operation(description = "Modifier un employé existant")
    @PutMapping("/modify-employe")
    public Employe modifyEmploye(@RequestBody Employe employe) {
        return employeService.update(employe.getIdEmploye(), employe);
    }

    @Operation(description = "Supprimer un employé par son identifiant")
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long employeId) {
        employeService.deleteById(employeId);
    }
}
