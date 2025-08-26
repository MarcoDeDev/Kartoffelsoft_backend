package com.farmit.kartoffelsoft_backend.controller;

import com.farmit.kartoffelsoft_backend.model.Bestellung;
import com.farmit.kartoffelsoft_backend.service.BestellungService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bestellung")
public class BestellungController {

    private final BestellungService bestellungService;

    public BestellungController(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }

    // Standard Methode
    // localhost:8080/api/bestellung
    @GetMapping
    public List<Bestellung> getAllBestellung(){
        return bestellungService.getAllBestellung();
    }

    @GetMapping ("/{id}")
    public Bestellung getBestellungById(@PathVariable("id") long id){
        return bestellungService.getBestellungById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bestellung not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public Bestellung save(@RequestBody Bestellung bestellung) {
        return bestellungService.save(bestellung);
    }

    @DeleteMapping ("/{id}")
    public void deleteBestellungById(@PathVariable("id") long id) {
        bestellungService.deleteBestellungById(id);
    }

}
