package com.farmit.kartoffelsoft_backend.controller;


import com.farmit.kartoffelsoft_backend.dto.LoginRequest;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import com.farmit.kartoffelsoft_backend.service.MitarbeiterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mitarbeiter")
public class MitarbeiterController {
    
    public final MitarbeiterService mitarbeiterService;
    public BCryptPasswordEncoder passwordEncoder;

    public MitarbeiterController(MitarbeiterService mitarbeiterService, BCryptPasswordEncoder passwordEncoder) {
        this.mitarbeiterService = mitarbeiterService;
        this.passwordEncoder = passwordEncoder;
    }

    // Standard Methode
    // localhost:8080/api/mitarbeiter
    @GetMapping
    public List<Mitarbeiter> getAllMitarbeiter(){
        return mitarbeiterService.getAllMitarbeiter();
    }

    @GetMapping ("/{id}")
    public Mitarbeiter getMitarbeiterById(@PathVariable("id") long id){
        return mitarbeiterService.getMitarbeiterById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mitarbeiter not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public Mitarbeiter register(@RequestBody Mitarbeiter mitarbeiter) {
        return mitarbeiterService.register(mitarbeiter);
    }

    @DeleteMapping ("/{id}")
    public void deleteMitarbeiterById(@PathVariable("id") long id) {
        mitarbeiterService.deleteMitarbeiterById(id);
    }

    @PostMapping ("/login")
    public ResponseEntity<Mitarbeiter> login(@RequestBody LoginRequest loginRequest){

        Optional<Mitarbeiter> optionalMitarbeiter = mitarbeiterService.findByUsername(loginRequest.getUsername());

        if (optionalMitarbeiter.isPresent()) {
            Mitarbeiter mitarbeiter = optionalMitarbeiter.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), mitarbeiter.getPassword())) {
                return ResponseEntity.ok(mitarbeiter);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
}
