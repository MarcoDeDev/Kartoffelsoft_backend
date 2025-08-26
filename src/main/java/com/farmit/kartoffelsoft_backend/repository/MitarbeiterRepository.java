package com.farmit.kartoffelsoft_backend.repository;

import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {

    Optional<Mitarbeiter> findByUsername(String username);
}
