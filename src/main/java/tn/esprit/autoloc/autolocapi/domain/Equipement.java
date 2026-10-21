package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 300)
    private String description;

    // Côté inverse de la relation ManyToMany (chargements et suppressions non liées)
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    private List<Vehicule> vehicules = new ArrayList<>();
}
