package com.farmit.kartoffelsoft_backend.service;

import com.farmit.kartoffelsoft_backend.dto.ArtikelRegistrationRequest;
import com.farmit.kartoffelsoft_backend.exception.LieferantNichtGefunden;
import com.farmit.kartoffelsoft_backend.model.Artikel;
import com.farmit.kartoffelsoft_backend.model.Lieferant;
import com.farmit.kartoffelsoft_backend.repository.ArtikelRepository;
import com.farmit.kartoffelsoft_backend.repository.LieferantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikelServiceImpl implements ArtikelService {

    private final ArtikelRepository artikelRepository;
    private final LieferantRepository lieferantRepository;

    public ArtikelServiceImpl(ArtikelRepository artikelRepository, LieferantRepository lieferantRepository) {
        this.artikelRepository = artikelRepository;
        this.lieferantRepository = lieferantRepository;
    }

    @Override
    public Artikel save(ArtikelRegistrationRequest artikelRegistrationRequest) {

        // Finde die Lieferant anhand der ID
        Lieferant lieferant = null;
        Long lieferantId = artikelRegistrationRequest.getLieferantId();

        if (lieferantId != null) {
            lieferant = lieferantRepository.findById(lieferantId)
                    .orElseThrow(() -> new LieferantNichtGefunden("Lieferant mit der ID " + lieferantId + " nicht gefunden."));
        }

        // Erstelle den neuen Artikel aus dem DTO
        Artikel neuerArtikel = new Artikel();
        neuerArtikel.setName(artikelRegistrationRequest.getName());
        neuerArtikel.setLieferant(lieferant);
        neuerArtikel.setMenge(artikelRegistrationRequest.getMenge());
        neuerArtikel.setWarenEinheit(artikelRegistrationRequest.getWarenEinheit());
        neuerArtikel.setWarenTyp(artikelRegistrationRequest.getWarenTyp());
        neuerArtikel.setPreisProEinheit(artikelRegistrationRequest.getPreisProEinheit());
        neuerArtikel.setVerdorbene(artikelRegistrationRequest.getVerdorbene());
        neuerArtikel.setRabat(artikelRegistrationRequest.getRabat());


        return artikelRepository.save(neuerArtikel);
    }

    @Override
    public Optional<Artikel> getArtikelById(long id) {
        return artikelRepository.findById(id);
    }

    @Override
    public List<Artikel> getAllArtikel() {
        return artikelRepository.findAll();
    }

    @Override
    public void deleteArtikelById(long id) {
        artikelRepository.deleteById(id);
    }
}
