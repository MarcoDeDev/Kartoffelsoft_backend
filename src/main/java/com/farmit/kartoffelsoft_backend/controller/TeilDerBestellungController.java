package com.farmit.kartoffelsoft_backend.controller;

import com.farmit.kartoffelsoft_backend.model.TeilDerBestellung;
import com.farmit.kartoffelsoft_backend.service.TeilDerBestellungService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teilderbestellung")
public class TeilDerBestellungController {
    
    private final TeilDerBestellungService teilDerBestellungService;

    public TeilDerBestellungController(TeilDerBestellungService teilDerBestellungService) {
        this.teilDerBestellungService = teilDerBestellungService;
    }

    // Standard Methode
    // localhost:8080/api/teilderbestellung
    @GetMapping
    public List<TeilDerBestellung> getAllTeilDerBestellung(){
        return teilDerBestellungService.getAllTeilDerBestellung();
    }

    @GetMapping ("/{id}")
    public TeilDerBestellung getTeilDerBestellungById(@PathVariable("id") long id){
        return teilDerBestellungService.getTeilDerBestellungById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teil der Bestellung not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public TeilDerBestellung save(@RequestBody TeilDerBestellung teilDerBestellung) {
        return teilDerBestellungService.save(teilDerBestellung);
    }

    @DeleteMapping ("/{id}")
    public void deleteTeilDerBestellungById(@PathVariable("id") long id) {
        teilDerBestellungService.deleteTeilDerBestellungById(id);
    }
    
}
