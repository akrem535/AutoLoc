package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Paiement;

import java.util.List;

// Paiement est lecture seule : pas de create/update/delete
public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();
}
