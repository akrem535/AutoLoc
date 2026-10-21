package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long>
        implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Reservation r, boolean isNew) {
        return r.getDateDebut() != null
            && r.getDateFin() != null
            && r.getMontantTotal() != null
            && r.getStatut() != null;
    }

    @Override protected String getEntityName() { return "Reservation"; }
    @Override protected Long getId(Reservation r) { return r.getIdReservation(); }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setMontantTotal(source.getMontantTotal());
        target.setStatut(source.getStatut());
    }
}
