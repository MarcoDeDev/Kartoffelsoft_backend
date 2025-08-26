package com.farmit.kartoffelsoft_backend.service;

import com.farmit.kartoffelsoft_backend.exception.UsernameAlreadyExistsException;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;

import java.util.List;
import java.util.Optional;

public interface MitarbeiterService {

    Optional<Mitarbeiter> getMitarbeiterById(long id);

    List<Mitarbeiter> getAllMitarbeiter();

    void deleteMitarbeiterById(long id);

    Optional<Mitarbeiter> findByUsername(String username);

    Mitarbeiter register(Mitarbeiter mitarbeiter);
}
