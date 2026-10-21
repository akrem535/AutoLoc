package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe, Long>
        implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Employe e, boolean isNew) {
        return Utils.isNotEmpty(e.getNom())
            && Utils.isNotEmpty(e.getPrenom())
            && Utils.isNotEmpty(e.getCin())
            && Utils.isNotEmpty(e.getEmail())
            && Utils.isNotEmpty(e.getPoste())
            && e.getDateEmbauche() != null
            && e.getSalaire() != null;
    }

    @Override protected String getEntityName() { return "Employe"; }
    @Override protected Long getId(Employe e) { return e.getIdEmploye(); }

    @Override
    protected void setFields(Employe source, Employe target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setCin(source.getCin());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setPoste(source.getPoste());
        target.setDateEmbauche(source.getDateEmbauche());
        target.setSalaire(source.getSalaire());
    }
}
