package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Sinistre;
import tn.esprit.autoloc.autolocapi.repository.ISinistreRepository;

@Service
public class SinistreServiceImpl extends GenericServiceImpl<Sinistre, Long>
        implements ISinistreService {

    public SinistreServiceImpl(ISinistreRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Sinistre s, boolean isNew) {
        return s.getDateSinistre() != null
            && Utils.isNotEmpty(s.getDescription())
            && s.getCoutReparation() != null
            && s.getPrisEnChargeAssurance() != null;
    }

    @Override protected String getEntityName() { return "Sinistre"; }
    @Override protected Long getId(Sinistre s) { return s.getIdSinistre(); }

    @Override
    protected void setFields(Sinistre source, Sinistre target) {
        target.setDateSinistre(source.getDateSinistre());
        target.setDescription(source.getDescription());
        target.setCoutReparation(source.getCoutReparation());
        target.setPrisEnChargeAssurance(source.getPrisEnChargeAssurance());
        target.setReferenceAssurance(source.getReferenceAssurance());
    }
}
