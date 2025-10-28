package com.lucasnovello.comidita_martes.service;


import com.lucasnovello.comidita_martes.model.Participante;
import com.lucasnovello.comidita_martes.repository.IParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService implements IParticipanteService{

    @Autowired
    private IParticipanteRepository participanteRepository;

    @Override
    public List<Participante> getParticipantes() {
        return participanteRepository.findAll();
    }

    @Override
    public Participante saveParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    @Override
    public void deleteParticipante(Long id) {
        participanteRepository.deleteById(id);
    }

    @Override
    public Participante findParticipante(Long id) {
        return participanteRepository.findById(id).orElse(null);
    }
}
