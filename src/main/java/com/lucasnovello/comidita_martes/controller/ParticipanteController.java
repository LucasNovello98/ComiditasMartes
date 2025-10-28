package com.lucasnovello.comidita_martes.controller;

import com.lucasnovello.comidita_martes.model.Participante;
import com.lucasnovello.comidita_martes.service.IParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private IParticipanteService participanteService;

    @GetMapping ("/lista-participantes")
    public ResponseEntity<List<Participante>> getParticipantes() {
        return ResponseEntity.ok(participanteService.getParticipantes());
    }

    // 201 Created
    @PostMapping ("/crear")
    public ResponseEntity<Participante> saveParticipante(@RequestBody Participante participante) {
        return ResponseEntity.status(201).body(participanteService.saveParticipante(participante));
    }

    @DeleteMapping ("/eliminar-participante/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        participanteService.deleteParticipante(id);
        return ResponseEntity.noContent().build(); // devuelve el codigo y con .build() crea el ResponseEntity
    }

    @GetMapping ("/buscar/{id}")
    public ResponseEntity<Participante> findParticipante (@PathVariable Long id) {
        Participante participante = participanteService.findParticipante(id);
        if (participante != null) {
            return ResponseEntity.ok(participante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }







}
