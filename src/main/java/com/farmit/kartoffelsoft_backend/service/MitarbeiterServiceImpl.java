package com.farmit.kartoffelsoft_backend.service;

import com.farmit.kartoffelsoft_backend.exception.UsernameAlreadyExistsException;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import com.farmit.kartoffelsoft_backend.repository.MitarbeiterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MitarbeiterServiceImpl implements MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MitarbeiterServiceImpl(MitarbeiterRepository mitarbeiterRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.passwordEncoder = passwordEncoder;
    }


    private Mitarbeiter save(Mitarbeiter mitarbeiter) {
        // Hache das Passwort, bevor es gespeichert wird
        mitarbeiter.setPassword(passwordEncoder.encode(mitarbeiter.getPassword()));
        return mitarbeiterRepository.save(mitarbeiter);
    }

    @Override
    public Optional<Mitarbeiter> getMitarbeiterById(long id) {
        return mitarbeiterRepository.findById(id);
    }

    @Override
    public List<Mitarbeiter> getAllMitarbeiter() {
        return mitarbeiterRepository.findAll();
    }

    @Override
    public void deleteMitarbeiterById(long id) {
        mitarbeiterRepository.deleteById(id);
    }

    @Override
    public Optional<Mitarbeiter> findByUsername(String username) {
        return mitarbeiterRepository.findByUsername(username);
    }

    @Override
    public Mitarbeiter register(Mitarbeiter mitarbeiter) {
        // Überprüfe, ob der Benutzername bereits existiert
        if(mitarbeiterRepository.findByUsername(mitarbeiter.getUsername()).isPresent()) {
          throw new UsernameAlreadyExistsException("Der Benutzername " + mitarbeiter.getUsername() + " ist bereits vergeben.");
        }

        return save(mitarbeiter);
    }
}