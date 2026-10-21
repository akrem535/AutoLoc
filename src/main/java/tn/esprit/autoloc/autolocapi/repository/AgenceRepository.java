package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.Agence;

public interface AgenceRepository extends CrudRepository<Agence, Long> {
}
