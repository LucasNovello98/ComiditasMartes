package com.lucasnovello.comidita_martes.controller;

import com.lucasnovello.comidita_martes.dto.ParticipanteRequestDTO;
import com.lucasnovello.comidita_martes.dto.ParticipanteResponseDTO;
import com.lucasnovello.comidita_martes.service.IParticipanteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    private final IParticipanteService participanteService;

    public ParticipanteController(IParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteResponseDTO>> getParticipantes() {
        return ResponseEntity.ok(participanteService.getParticipantes());
    }

    // 201 Created
    @PostMapping
    public ResponseEntity<ParticipanteResponseDTO> saveParticipante(@Valid @RequestBody ParticipanteRequestDTO participanteDto) {
        return ResponseEntity.status(201).body(participanteService
                .createParticipante(participanteDto));
    }

    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        participanteService.deleteParticipante(id);
        return ResponseEntity.noContent().build(); // devuelve el codigo y con .build() crea el ResponseEntity
    }

    @GetMapping ("/buscar/{id}")
    public ResponseEntity<ParticipanteResponseDTO> findParticipante (@PathVariable Long id) {
        ParticipanteResponseDTO participante = participanteService.findParticipante(id);
        if (participante != null) {
            return ResponseEntity.ok(participante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ParticipanteResponseDTO> updateParticipante(@Valid @RequestBody ParticipanteRequestDTO participanteDto,
                                                                      @PathVariable Long id) {
        ParticipanteResponseDTO participante = participanteService.updateParticipante(participanteDto, id);
        return ResponseEntity.ok(participante);
    }


}
