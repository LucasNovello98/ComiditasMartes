package com.lucasnovello.comidita_martes.service;

import com.lucasnovello.comidita_martes.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IParticipanteService{

    // traer participantes
    public List<Participante> getParticipantes();

    // guardar participante
    public Participante saveParticipante(Participante participante);

    // borrar participante por id
    public void deleteParticipante(Long id);

    // buscar participante por id
    public Participante findParticipante(Long id);

}
