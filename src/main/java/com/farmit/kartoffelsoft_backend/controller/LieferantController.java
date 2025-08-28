package com.farmit.kartoffelsoft_backend.controller;

import com.farmit.kartoffelsoft_backend.model.Lieferant;
import com.farmit.kartoffelsoft_backend.service.LieferantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lieferant")
public class LieferantController {

    private final LieferantService lieferantService;

    public LieferantController(LieferantService lieferantService) {
        this.lieferantService = lieferantService;
    }

    // Standard Methode
    // localhost:8080/api/lieferant
    @GetMapping
    public List<Lieferant> getAllLieferant(){
        return lieferantService.getAllLieferant();
    }

    @GetMapping ("/{id}")
    public Lieferant getLieferantById(@PathVariable("id") long id){
        return lieferantService.getLieferantById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lieferant not found"));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public Lieferant save(@RequestBody Lieferant lieferant) {
        return lieferantService.save(lieferant);
    }

    @DeleteMapping ("/{id}")
    public void deleteLieferantById(@PathVariable("id") long id) {
        lieferantService.deleteLieferantById(id);
    }
}
