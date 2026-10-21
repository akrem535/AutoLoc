package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "entretien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntretien;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TypeEntretien type;

    @Column(nullable = false)
    private LocalDate dateEntretien;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cout;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 100)
    private String prestataire;

    private LocalDate prochaineEcheance;

    // FK vers véhicule dans la table entretien
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicule vehicule;
}
