package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.*;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    // Dépôt de base (CrudRepository)
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    // Dépôt complet (JpaRepository)
    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // Service agence
    @Autowired
    private IAgenceService agenceService;

    // ── Méthode privée utilitaire ────────────────────────────────────────────
    private void addAgence(CrudRepository<Agence, Long> repository) {
        int suffix = (int) System.currentTimeMillis();

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("TU" + suffix);
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("TU" + (suffix + 1));
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.CITADINE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setVille("Tunis");
        agence.setTelephone("71585874");

        v1.setAgence(agence);
        v2.setAgence(agence);
        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        repository.save(agence);
    }

    // ── Tests d'ajout ────────────────────────────────────────────────────────
    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // ── Tests de chargement ──────────────────────────────────────────────────
    private void loadAgence(CrudRepository<Agence, Long> repository, String repoType) {
        Iterable<Agence> agences = repository.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("=== Dépôt utilisé : ").append(repoType).append(" ===\n");

        for (Agence agence : agences) {
            sb.append(agence.getIdAgence()).append(" | ").append(agence.getNom()).append("\n");
            sb.append("Vehicules Count : ").append(agence.getVehicules().size()).append("\n");
            for (Vehicule v : agence.getVehicules()) {
                sb.append("=== ").append(v.getIdVehicule())
                  .append("|").append(v.getImmatriculation()).append("\n");
            }
        }
        fail(sb.toString());
    }

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "CrudRepository");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "JpaRepository");
    }

    // ── Tri ──────────────────────────────────────────────────────────────────
    @Test
    public void loadSortedAgences() {
        Sort sort = Sort.by("idAgence").descending();
        List<Agence> agences = fullAgenceRepository.findAll(sort);
        StringBuilder sb = new StringBuilder();
        sb.append("=== Agences triées par id décroissant ===\n");

        for (Agence agence : agences) {
            sb.append(agence.getIdAgence())
              .append(" | ").append(agence.getNom())
              .append(" | ").append(agence.getVille()).append("\n");
        }
        fail(sb.toString());
    }

    // ── Pagination ───────────────────────────────────────────────────────────
    @Test
    public void loadPagedAgences() {
        Sort sort = Sort.by("idAgence").descending();
        PageRequest pageRequest = PageRequest.of(0, 2, sort);
        Page<Agence> page = fullAgenceRepository.findAll(pageRequest);

        StringBuilder sb = new StringBuilder();
        sb.append("=== Pagination : page ").append(page.getNumber())
          .append(" / ").append(page.getTotalPages()).append(" pages")
          .append(" (").append(page.getTotalElements()).append(" agences total) ===\n");

        for (Agence agence : page.getContent()) {
            sb.append(agence.getIdAgence())
              .append(" | ").append(agence.getNom())
              .append(" | ").append(agence.getVille()).append("\n");
        }
        fail(sb.toString());
    }
    // ── Test service ─────────────────────────────────────────────────────────
    @Test
    public void createAgence() {
        int suffix = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setNom("Agence Service Test");
        agence.setAdresse("10 Rue de la Paix");
        agence.setVille("Tunis");
        agence.setTelephone("7100000" + (suffix % 100));

        Agence saved = agenceService.create(agence);
        fail("Agence créée avec id=" + saved.getIdAgence()
            + " | nom=" + saved.getNom()
            + " | ville=" + saved.getVille());
    }
}

// Interface en dehors de la classe — Spring Data peut la détecter comme bean
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}
