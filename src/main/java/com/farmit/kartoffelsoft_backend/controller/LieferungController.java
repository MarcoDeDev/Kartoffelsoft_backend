package com.farmit.kartoffelsoft_backend.controller;

import com.farmit.kartoffelsoft_backend.model.Lieferung;
import com.farmit.kartoffelsoft_backend.service.LieferungService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lieferung")
public class LieferungController {
    
    private final LieferungService lieferungService;

    public LieferungController(LieferungService lieferungService) {
        this.lieferungService = lieferungService;
    }

    // Standard Methode
    // localhost:8080/api/lieferung
    @GetMapping
    public List<Lieferung> getAllLieferung(){
        return lieferungService.getAllLieferung();
    }

    @GetMapping ("/{id}")
    public Lieferung getLieferungById(@PathVariable("id") long id){
        return lieferungService.getLieferungById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lieferung not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 No Content ist Standard für erfolgreiches Created
    public Lieferung save(@RequestBody Lieferung lieferung) {
        return lieferungService.save(lieferung);
    }

    @DeleteMapping ("/{id}")
    public void deleteLieferungById(@PathVariable("id") long id) {
        lieferungService.deleteLieferungById(id);
    }
    
}
