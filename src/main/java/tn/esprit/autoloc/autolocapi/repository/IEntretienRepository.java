package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Entretien;

public interface IEntretienRepository extends JpaRepository<Entretien, Long> {
}
