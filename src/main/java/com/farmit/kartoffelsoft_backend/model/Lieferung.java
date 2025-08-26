package com.farmit.kartoffelsoft_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data // Lombok-Annotation: erzeugt automatisch Getter, Setter, toString, equals, hashCode und RequiredArgsConstructor
@Entity
public class Lieferung {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    private LocalDateTime datum;

    @ManyToOne // Viele Lieferungen können von einem Lieferanten gemacht werden
    @JoinColumn(name = "lieferantId") // Fremdschlüssel Spalte
    @JsonProperty("lieferantId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Lieferant lieferant;

    private double gesamterPreis;
}
