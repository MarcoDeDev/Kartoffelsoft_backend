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
public class TeilDerLieferung {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    @ManyToOne // Ein Artikel kann von mehreren TeilDerLieferung Teil sein
    @JoinColumn(name = "artikelId", nullable = false) // Fremdschlüssel Spalte
    @JsonProperty("artikelId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Artikel artikel;

    @ManyToOne // Eine Lieferung kann mehrere TeilDerLieferung enthalten
    @JoinColumn(name = "lieferungId", nullable = false) // Fremdschlüssel Spalte
    @JsonProperty("lieferungId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Lieferung lieferung;

    private int menge;
    private double preis;
}
