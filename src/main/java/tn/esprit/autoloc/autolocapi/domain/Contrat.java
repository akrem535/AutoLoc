package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false, unique = true, length = 30)
    private String numeroContrat;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(precision = 10, scale = 2)
    private BigDecimal kilometrageDepart;

    @Column(precision = 10, scale = 2)
    private BigDecimal kilometrageRetour;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutContrat statut;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    // Chargement contrat implique chargement des paiements (EAGER)
    @OneToMany(mappedBy = "contrat", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
