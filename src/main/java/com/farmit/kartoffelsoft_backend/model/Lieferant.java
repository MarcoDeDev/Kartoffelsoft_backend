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
public class Lieferant {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    @OneToMany(mappedBy = "lieferant", cascade = CascadeType.ALL, orphanRemoval = true) // Eine Bestellung enthält viele TeilDerBestellung
    @JsonIgnore // Vermeidet Endlosschleifen
    private Set<Artikel> artikels = new HashSet<>();

    @OneToMany(mappedBy = "lieferant", cascade = CascadeType.ALL, orphanRemoval = true) // Eine Bestellung enthält viele TeilDerBestellung
    @JsonIgnore // Vermeidet Endlosschleifen
    private Set<Lieferung> lieferungen = new HashSet<>();

    private String firmaName;
    private String strasse;
    private String plz;
    private String ort;
    private String emailAdresse;
    private String telefon;
}
