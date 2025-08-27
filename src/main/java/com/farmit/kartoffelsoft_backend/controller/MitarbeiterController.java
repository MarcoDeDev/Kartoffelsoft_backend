package com.farmit.kartoffelsoft_backend.controller;


import com.farmit.kartoffelsoft_backend.dto.LoginRequest;
import com.farmit.kartoffelsoft_backend.model.Mitarbeiter;
import com.farmit.kartoffelsoft_backend.service.MitarbeiterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mitarbeiter")
public class MitarbeiterController {
    
    public final MitarbeiterService mitarbeiterService;
    private final AuthenticationManager authenticationManager;

    public MitarbeiterController(MitarbeiterService mitarbeiterService, AuthenticationManager authenticationManager) {
        this.mitarbeiterService = mitarbeiterService;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/login")
    public ResponseEntity<Mitarbeiter> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("LoginRequest Username: " + loginRequest.getUsername());
            System.out.println("LoginRequest Password: " + loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Nach erfolgreicher Authentifizierung ist der Benutzer im "authentication"-Objekt verfügbar.
            // Wir können den Benutzernamen aus dem principal des authentifizierten Objekts ziehen
            // und den Mitarbeiter aus dem Service abfragen, um das vollständige Objekt zurückzugeben.
            Mitarbeiter mitarbeiter = mitarbeiterService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mitarbeiter not found after authentication"));

            return ResponseEntity.ok(mitarbeiter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
