package com.farmit.kartoffelsoft_backend.service;

import com.farmit.kartoffelsoft_backend.model.Abteilung;

import java.util.List;
import java.util.Optional;

public interface AbteilungService {

    Abteilung save(Abteilung abteilung);

    Optional<Abteilung> getAbteilungById(long id);

    List<Abteilung> getAllAbteilung();

    void deleteAbteilungById(long id);

    Optional<Abteilung> findByAbteilungName(String name);
}
