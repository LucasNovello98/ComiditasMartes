package com.lucasnovello.comidita_martes.controller;

import com.lucasnovello.comidita_martes.dto.ComidaRequestDTO;
import com.lucasnovello.comidita_martes.dto.ComidaResponseDTO;
import com.lucasnovello.comidita_martes.service.IComidaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/api/comidas")
public class ComidaController {

    private final IComidaService comidaService;

    public ComidaController(IComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @GetMapping
    public ResponseEntity<List<ComidaResponseDTO>> getComidas() {
        return ResponseEntity.ok(comidaService.getComidas());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ComidaResponseDTO> findComida (@PathVariable Long id) {
        ComidaResponseDTO comida = comidaService.findComida(id);
        if (comida != null) {
            return ResponseEntity.ok(comida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 201 Created
    @PostMapping
    public ResponseEntity<ComidaResponseDTO> saveComida(@Valid @RequestBody ComidaRequestDTO comidaDTO) {
        return ResponseEntity.status(201).body(comidaService.createComida(comidaDTO));
    }

    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteComida(@PathVariable Long id) {
        comidaService.deleteComida(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping ("/{comidaId}/participante/{participanteId}")
    public ResponseEntity<ComidaResponseDTO> addParticipante(@PathVariable Long comidaId, @PathVariable Long participanteId) {
        try {
            ComidaResponseDTO comidaActualiazda = comidaService.addParticipante(comidaId, participanteId);
            return ResponseEntity.ok(comidaActualiazda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/eliminar/comida/{comidaId}/participante/{participanteId}")
    public ResponseEntity<ComidaResponseDTO> removeParticipante(@PathVariable Long comidaId, @PathVariable Long participanteId) {
        ComidaResponseDTO comidaActualizada = comidaService.removeParticipante(comidaId, participanteId);
        return ResponseEntity.ok(comidaActualizada);
    }
}
