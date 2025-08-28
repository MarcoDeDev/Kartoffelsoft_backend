package com.farmit.kartoffelsoft_backend.controller;


import com.farmit.kartoffelsoft_backend.model.Abteilung;
import com.farmit.kartoffelsoft_backend.service.AbteilungService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/abteilung")
public class AbteilungController {

    public final AbteilungService abteilungService;

    public AbteilungController(AbteilungService abteilungService) {
        this.abteilungService = abteilungService;
    }


    // Standard Methode
    // localhost:8080/api/abteilung
    @GetMapping
    public List<Abteilung> getAllAbteilung(){
        return abteilungService.getAllAbteilung();
    }

    @GetMapping ("/{id}")
    public Abteilung getAbteilungById(@PathVariable("id") long id){
        return abteilungService.getAbteilungById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abteilung not found"));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public Abteilung save(@RequestBody Abteilung abteilung) {
        return abteilungService.save(abteilung);
    }

    @DeleteMapping ("/{id}")
    public void deleteAbteilungById(@PathVariable("id") long id) {
        abteilungService.deleteAbteilungById(id);
    }
}
