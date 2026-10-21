package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 20)
    private String cin;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telephone;

    @Column(nullable = false, length = 50)
    private String poste;

    @Column(nullable = false)
    private LocalDate dateEmbauche;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salaire;

    // Suppression agence n'implique pas suppression des employés (pas de cascade delete)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_agence")
    private Agence agence;
}
