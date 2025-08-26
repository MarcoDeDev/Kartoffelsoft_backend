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
public class Mitarbeiter {

    @Id // (jakarta.persistence) als PRIMARY KEY zugewiesen
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private long id;

    private String vorname;
    private String nachname;

    @ManyToOne // Viele Mitarbeiter können einer Abteilung gehören
    @JoinColumn(name = "abteilungId", nullable = false) // Fremdschlüssel Spalte
    @JsonProperty("abteilungId") // um dem Frontend den richtigen Schlüssel zu kommunizieren
    private Abteilung abteilung;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
