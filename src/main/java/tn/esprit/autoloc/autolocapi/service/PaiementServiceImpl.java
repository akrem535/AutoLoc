package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IPaiementRepository;

import java.util.List;

// Paiement est lecture seule : pas de create/update/delete
@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Paiement", String.valueOf(id)));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}
