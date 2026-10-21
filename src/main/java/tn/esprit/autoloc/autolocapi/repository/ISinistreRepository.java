package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Sinistre;

public interface ISinistreRepository extends JpaRepository<Sinistre, Long> {
}
