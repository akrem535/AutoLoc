package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    
    @JsonIgnore
    @OneToMany(mappedBy = "agence", fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Vehicule> vehicules = new ArrayList<>();


    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employe> employes = new ArrayList<>();
}
