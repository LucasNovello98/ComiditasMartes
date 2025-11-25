package com.lucasnovello.comidita_martes.mapper;

import com.lucasnovello.comidita_martes.dto.ParticipanteRequestDTO;
import com.lucasnovello.comidita_martes.dto.ParticipanteResponseDTO;
import com.lucasnovello.comidita_martes.model.Participante;

public class ParticipanteMapper {

    public static Participante toEntity(ParticipanteRequestDTO dto) {
        return Participante.builder()
                .nombre(dto.getNombre())
                .build();
    }

    public static ParticipanteResponseDTO toResponse(Participante participante) {
        return ParticipanteResponseDTO.builder()
                .id(participante.getId())
                .nombre(participante.getNombre())
                .cantidadAsistencias(participante.getListaComida() != null ? participante.getListaComida().size() : 0)
                .build();
    }
}
