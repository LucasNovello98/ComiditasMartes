package com.lucasnovello.comidita_martes.controller;

import com.lucasnovello.comidita_martes.model.Comida;
import com.lucasnovello.comidita_martes.service.IComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/comidas")
public class ComidaController {

    @Autowired
    private IComidaService comidaService;

    @GetMapping ("/")
    public ResponseEntity<List<Comida>> getComidas() {
        return ResponseEntity.ok(comidaService.getComidas());
    }

    // 201 Created
    @PostMapping ("/")
    public ResponseEntity<Comida> saveComida(@RequestBody Comida comida) {
        return ResponseEntity.status(201).body(comidaService.saveComida(comida));
    }

    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteComida(@PathVariable Long id) {
        comidaService.deleteComida(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/buscar/{id}")
    public ResponseEntity<Comida> findComida (@PathVariable Long id) {
        Comida comida = comidaService.findComida(id);
        if (comida != null) {
            return ResponseEntity.ok(comida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/{comidaId}/participante/{participanteId}")
    public ResponseEntity<Comida> addParticipante(@PathVariable Long comidaId, @PathVariable Long participanteId) {
        try {
            Comida comidaActualiazda = comidaService.addParticipante(comidaId, participanteId);
            return ResponseEntity.ok(comidaActualiazda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/eliminar/comida/{comidaId}/participante/{participanteId}")
    public ResponseEntity<Comida> removeParticipante(@PathVariable Long comidaId, @PathVariable Long participanteId) {
        Comida comidaActualizada = comidaService.removeParticipante(comidaId, participanteId);
        return ResponseEntity.ok(comidaActualizada);
    }





}
