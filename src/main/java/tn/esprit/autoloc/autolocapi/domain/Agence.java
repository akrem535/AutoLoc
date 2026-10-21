package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 200)
    private String adresse;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(length = 20)
    private String telephone;

    @Column(length = 100)
    private String email;

    // Une agence possède plusieurs véhicules (EAGER : chargement d'une agence => chargement des véhicules)
    // CascadeType.PERSIST/MERGE : l'ajout d'un véhicule implique la présence de son agence
    @OneToMany(mappedBy = "agence", fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Vehicule> vehicules = new ArrayList<>();

    // Une agence possède plusieurs employés (LAZY : chargement agence n'implique pas employés)
    // Pas de cascade delete : suppression agence n'implique pas suppression des employés
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employe> employes = new ArrayList<>();
}
