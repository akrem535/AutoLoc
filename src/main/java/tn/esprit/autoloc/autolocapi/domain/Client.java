package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

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

    @Column(length = 200)
    private String adresse;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false, unique = true, length = 30)
    private String numeroPermis;

    @Column(nullable = false)
    private LocalDate dateExpirationPermis;

    // Chargement client implique chargement de ses réservations (EAGER)
    // Suppression client implique suppression de ses réservations (CASCADE ALL + orphanRemoval)
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
