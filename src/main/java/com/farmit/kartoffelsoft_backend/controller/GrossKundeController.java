package com.farmit.kartoffelsoft_backend.controller;

import com.farmit.kartoffelsoft_backend.model.GrossKunde;
import com.farmit.kartoffelsoft_backend.service.GrossKundeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grosskunde")
public class GrossKundeController {

    private final GrossKundeService grossKundeService;

    public GrossKundeController(GrossKundeService grossKundeService) {
        this.grossKundeService = grossKundeService;
    }

    // Standard Methode
    // localhost:8080/api/grossKunde
    @GetMapping
    public List<GrossKunde> getAllGrossKunde(){
        return grossKundeService.getAllGrossKunde();
    }

    @GetMapping ("/{id}")
    public GrossKunde getGrossKundeById(@PathVariable("id") long id){
        return grossKundeService.getGrossKundeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Groß Kunde not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public GrossKunde save(@RequestBody GrossKunde grossKunde) {
        return grossKundeService.save(grossKunde);
    }

    @DeleteMapping ("/{id}")
    public void deleteGrossKundeById(@PathVariable("id") long id) {
        grossKundeService.deleteGrossKundeById(id);
    }

}
