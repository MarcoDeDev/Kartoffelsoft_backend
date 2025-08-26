package com.farmit.kartoffelsoft_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data // Lombok-Annotation: erzeugt automatisch Getter, Setter, toString, equals, hashCode und RequiredArgsConstructor
@Entity
public class Abteilung {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    @OneToMany(mappedBy = "abteilung", cascade = CascadeType.ALL, orphanRemoval = true) // Eine Abteilung kann viele Mitarbeiter enthalten
    @JsonIgnore // Vermeidet Endlosschleifen: Abteilung → Mitarbeiter → Abteilung → Mitarbeiter usw.
    private Set<Mitarbeiter> mitarbeiters = new HashSet<>();

    private String abteilungName;
}
