package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Entretien;
import tn.esprit.autoloc.autolocapi.repository.IEntretienRepository;

@Service
public class EntretienServiceImpl extends GenericServiceImpl<Entretien, Long>
        implements IEntretienService {

    public EntretienServiceImpl(IEntretienRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Entretien e, boolean isNew) {
        return e.getType() != null
            && e.getDateEntretien() != null
            && e.getCout() != null
            && Utils.isNotEmpty(e.getPrestataire());
    }

    @Override protected String getEntityName() { return "Entretien"; }
    @Override protected Long getId(Entretien e) { return e.getIdEntretien(); }

    @Override
    protected void setFields(Entretien source, Entretien target) {
        target.setType(source.getType());
        target.setDateEntretien(source.getDateEntretien());
        target.setCout(source.getCout());
        target.setDescription(source.getDescription());
        target.setPrestataire(source.getPrestataire());
        target.setProchaineEcheance(source.getProchaineEcheance());
    }
}
