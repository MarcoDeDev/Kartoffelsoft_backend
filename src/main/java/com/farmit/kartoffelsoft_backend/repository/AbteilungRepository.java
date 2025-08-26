package com.farmit.kartoffelsoft_backend.repository;

import com.farmit.kartoffelsoft_backend.model.Abteilung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbteilungRepository extends JpaRepository <Abteilung, Long>{

    Optional<Abteilung> findByAbteilungName(String name);
}
