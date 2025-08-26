package com.farmit.kartoffelsoft_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data // Lombok-Annotation: erzeugt automatisch Getter, Setter, toString, equals, hashCode und RequiredArgsConstructor
@Entity
public class Bestellung {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    @ManyToOne // Viele Bestellungen können von einem Kunden gemacht werden
    @JoinColumn(name = "grossKundeId") // Fremdschlüssel Spalte
    @JsonProperty("grossKundeId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private GrossKunde grossKunde;

    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.ALL, orphanRemoval = true) // Eine Bestellung enthält viele TeilDerBestellung
    @JsonIgnore // Vermeidet Endlosschleifen
    private Set<TeilDerBestellung> teilDerBestellungen = new HashSet<>();

    private LocalDateTime datum;
    private double gesamterPreis;
    private boolean bezahlt;
    private String zahlungArt;
    private int rabat;
}
