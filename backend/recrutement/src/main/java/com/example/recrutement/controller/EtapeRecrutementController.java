package com.example.recrutement.controller;

import com.example.recrutement.dto.EtapeDTO;
import com.example.recrutement.dto.EtapeListDTO;
import com.example.recrutement.entity.EtapeRecrutement;
import com.example.recrutement.service.EtapeRecrutementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etapes")
public class EtapeRecrutementController {

    @Autowired
    private EtapeRecrutementService etapeService;

    @PostMapping("/create/{demandeId}")
    public ResponseEntity<EtapeDTO<EtapeRecrutement>> createEtape(@PathVariable Long demandeId, @RequestBody EtapeRecrutement etape) {
        return ResponseEntity.ok(etapeService.createEtape(demandeId, etape));
    }

    @PutMapping("/update/{etapeId}")
    public ResponseEntity<EtapeDTO<EtapeRecrutement>> updateEtape(@PathVariable Long etapeId, @RequestBody EtapeRecrutement etape) {
        return ResponseEntity.ok(etapeService.updateEtape(etapeId, etape));
    }

    @GetMapping("/all")
    public ResponseEntity<EtapeDTO<List<EtapeRecrutement>>> getAllEtapes() {
        return ResponseEntity.ok(etapeService.getAllEtapes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtapeDTO<EtapeRecrutement>> getEtapeById(@PathVariable Long id) {
        return ResponseEntity.ok(etapeService.getEtapeById(id));
    }

    @GetMapping("/demande/{demandeId}")
    public ResponseEntity<EtapeListDTO> getEtapesByDemandeId(@PathVariable Long demandeId) {
        return ResponseEntity.ok(etapeService.getEtapesByDemandeId(demandeId));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EtapeDTO<Void>> deleteEtape(@PathVariable Long id) {
        return ResponseEntity.ok(etapeService.deleteEtape(id));
    }
}
