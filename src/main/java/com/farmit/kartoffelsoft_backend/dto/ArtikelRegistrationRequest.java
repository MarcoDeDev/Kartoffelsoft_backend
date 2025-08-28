package com.farmit.kartoffelsoft_backend.dto;

import com.farmit.kartoffelsoft_backend.model.WarenEinheit;
import com.farmit.kartoffelsoft_backend.model.WarenTyp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtikelRegistrationRequest {

    private String name;
    private Long lieferantId;
    private int menge;
    private WarenEinheit warenEinheit;
    private WarenTyp warenTyp;
    private double preisProEinheit;
    private int verdorbene;
    private int rabat;
}