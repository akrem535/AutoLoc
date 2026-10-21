package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.Utils;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long>
        implements IClientService {

    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Client c, boolean isNew) {
        return Utils.isNotEmpty(c.getNom())
            && Utils.isNotEmpty(c.getPrenom())
            && Utils.isNotEmpty(c.getCin())
            && Utils.isNotEmpty(c.getEmail())
            && c.getDateNaissance() != null
            && c.getDateExpirationPermis() != null;
    }

    @Override protected String getEntityName() { return "Client"; }
    @Override protected Long getId(Client c) { return c.getIdClient(); }

    @Override
    protected void setFields(Client source, Client target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setCin(source.getCin());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setAdresse(source.getAdresse());
        target.setDateNaissance(source.getDateNaissance());
        target.setNumeroPermis(source.getNumeroPermis());
        target.setDateExpirationPermis(source.getDateExpirationPermis());
    }
}
