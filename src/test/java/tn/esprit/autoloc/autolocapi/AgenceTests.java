package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    // Interface mock qui hérite de CrudRepository
    interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
    }

    @Test
    public void addAgence() {
        // Création des véhicules
        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.CITADINE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        // Création de l'agence
        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setVille("Tunis");
        agence.setTelephone("71585874");

        // Lier les véhicules à l'agence
        v1.setAgence(agence);
        v2.setAgence(agence);
        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        // Persister
        agenceRepository.save(agence);
    }

    @Test
    public void loadAgence() {
        Iterable<Agence> agences = agenceRepository.findAll();
        StringBuilder sb = new StringBuilder();

        for (Agence agence : agences) {
            sb.append(agence.getIdAgence()).append(" | ").append(agence.getNom()).append("\n");
            sb.append("Vehicules Count : ").append(agence.getVehicules().size()).append("\n");
            for (Vehicule v : agence.getVehicules()) {
                sb.append("=== ").append(v.getIdVehicule())
                  .append("|").append(v.getImmatriculation()).append("\n");
            }
        }

        // Assertion qui échoue toujours pour afficher le résultat
        fail(sb.toString());
    }
}
