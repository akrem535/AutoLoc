package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long>
        implements IContratService {

    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Contrat c, boolean isNew) {
        return Utils.isNotEmpty(c.getNumeroContrat())
            && c.getDateDebut() != null
            && c.getDateFin() != null
            && c.getMontantTotal() != null
            && c.getStatut() != null;
    }

    @Override protected String getEntityName() { return "Contrat"; }
    @Override protected Long getId(Contrat c) { return c.getIdContrat(); }

    @Override
    protected void setFields(Contrat source, Contrat target) {
        target.setNumeroContrat(source.getNumeroContrat());
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setKilometrageDepart(source.getKilometrageDepart());
        target.setKilometrageRetour(source.getKilometrageRetour());
        target.setMontantTotal(source.getMontantTotal());
        target.setStatut(source.getStatut());
    }
}
