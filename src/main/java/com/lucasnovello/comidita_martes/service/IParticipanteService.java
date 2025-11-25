package com.lucasnovello.comidita_martes.service;

import com.lucasnovello.comidita_martes.dto.ParticipanteRequestDTO;
import com.lucasnovello.comidita_martes.dto.ParticipanteResponseDTO;
import java.util.List;

public interface IParticipanteService{

    // traer participantes
    List<ParticipanteResponseDTO> getParticipantes();

    // buscar participante por id
    ParticipanteResponseDTO findParticipante(Long id);

    // guardar participante
    ParticipanteResponseDTO createParticipante(ParticipanteRequestDTO participanteDto);

    // borrar participante por id
    void deleteParticipante(Long id);

    // actualizar datos participante
    ParticipanteResponseDTO updateParticipante(ParticipanteRequestDTO participanteDto,
                                               Long id);


}
