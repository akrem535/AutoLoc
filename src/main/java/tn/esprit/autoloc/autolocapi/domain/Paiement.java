package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false)
    private LocalDateTime datePaiement;

    @Column(nullable = false, length = 30)
    private String modePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutPaiement statut;

    @Column(length = 100)
    private String referenceTransaction;

    // FK vers contrat dans la table paiement
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrat", nullable = false)
    private Contrat contrat;
}
