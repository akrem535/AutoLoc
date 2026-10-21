package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sinistre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSinistre;

    @Column(nullable = false)
    private LocalDate dateSinistre;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal coutReparation;

    @Column(nullable = false)
    private Boolean prisEnChargeAssurance;

    @Column(length = 50)
    private String referenceAssurance;

    // Un sinistre est lié à un véhicule
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicule vehicule;
}
