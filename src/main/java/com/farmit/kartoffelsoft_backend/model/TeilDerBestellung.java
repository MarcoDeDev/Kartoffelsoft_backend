package com.farmit.kartoffelsoft_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // Lombok-Annotation: erzeugt automatisch Getter, Setter, toString, equals, hashCode und RequiredArgsConstructor
@Entity
public class TeilDerBestellung {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    @ManyToOne // Viele TeilDerBestellung können einen Artikel enthalten
    @JoinColumn(name = "artikelId", nullable = false) // Fremdschlüssel Spalte
    @JsonProperty("artikelId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Artikel artikel;

    @ManyToOne // Viele TeilDerBestellung können einer Bestellung gehören
    @JoinColumn(name = "bestellungId", nullable = false) // Fremdschlüssel Spalte
    @JsonProperty("bestellungId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Bestellung bestellung;

    private int menge;
    private double preis;
}
