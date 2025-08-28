package com.farmit.kartoffelsoft_backend.service;

import com.farmit.kartoffelsoft_backend.dto.MitarbeiterRegistrationRequest;
import com.farmit.kartoffelsoft_backend.exception.AbteilungNichtGefunden;
import com.farmit.kartoffelsoft_backend.exception.UsernameAlreadyExistsException;
import com.farmit.kartoffelsoft_backend.model.Abteilung;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import com.farmit.kartoffelsoft_backend.repository.AbteilungRepository;
import com.farmit.kartoffelsoft_backend.repository.MitarbeiterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MitarbeiterServiceImpl implements MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final AbteilungRepository abteilungRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MitarbeiterServiceImpl(MitarbeiterRepository mitarbeiterRepository, AbteilungRepository abteilungRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.abteilungRepository = abteilungRepository;
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
    public Mitarbeiter register(MitarbeiterRegistrationRequest mitarbeiterRegistrationRequest) {
        // Überprüfe, ob der Benutzername bereits existiert
        if(mitarbeiterRepository.findByUsername(mitarbeiterRegistrationRequest.getUsername()).isPresent()) {
          throw new UsernameAlreadyExistsException("Der Benutzername " + mitarbeiterRegistrationRequest.getUsername() + " ist bereits vergeben.");
        }

        // Finde die Abteilung anhand der ID
        Abteilung abteilung = abteilungRepository.findById(mitarbeiterRegistrationRequest.getAbteilungId())
                .orElseThrow(() -> new AbteilungNichtGefunden("Abteilung mit der ID " + mitarbeiterRegistrationRequest.getAbteilungId() + " nicht gefunden."));

        // Erstelle den neuen Mitarbeiter aus dem DTO
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter();
        neuerMitarbeiter.setVorname(mitarbeiterRegistrationRequest.getVorname());
        neuerMitarbeiter.setNachname(mitarbeiterRegistrationRequest.getNachname());
        neuerMitarbeiter.setUsername(mitarbeiterRegistrationRequest.getUsername());
        neuerMitarbeiter.setPassword(mitarbeiterRegistrationRequest.getPassword()); // Das Passwort wird in save() gehasht
        neuerMitarbeiter.setRole(mitarbeiterRegistrationRequest.getRole());
        neuerMitarbeiter.setAbteilung(abteilung);

        return save(neuerMitarbeiter);
    }
}