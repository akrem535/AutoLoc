package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;

import java.util.List;

@Tag(name = "Gestion des Clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @Operation(description = "Récupérer tous les clients de la base de données")
    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        return clientService.findAll();
    }

    @Operation(description = "Récupérer un client par son identifiant")
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.findById(clientId);
    }

    @Operation(description = "Ajouter un nouveau client")
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client client) {
        return clientService.create(client);
    }

    @Operation(description = "Modifier un client existant")
    @PutMapping("/modify-client")
    public Client modifyClient(@RequestBody Client client) {
        return clientService.update(client.getIdClient(), client);
    }

    @Operation(description = "Supprimer un client par son identifiant")
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.deleteById(clientId);
    }
}
