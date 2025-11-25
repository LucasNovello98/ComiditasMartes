package com.lucasnovello.comidita_martes.mapper;

import com.lucasnovello.comidita_martes.dto.ComidaRequestDTO;
import com.lucasnovello.comidita_martes.dto.ComidaResponseDTO;
import com.lucasnovello.comidita_martes.model.Comida;
import com.lucasnovello.comidita_martes.model.Participante;
import java.util.List;
import java.util.stream.Collectors;

public class ComidaMapper {

    public static Comida toEntity(ComidaRequestDTO dto, List<Participante> participantes) {

        return Comida.builder()
                .fecha(dto.getFecha())
                .lugar(dto.getLugar())
                .comida(dto.getComida())
                .cocinero(dto.getCocinero())
                .participantes(participantes)
                .build();
    }

    public static ComidaResponseDTO toResponse(Comida comida) {
        return ComidaResponseDTO.builder()
                .id(comida.getId())
                .fecha(comida.getFecha())
                .lugar(comida.getLugar())
                .comida(comida.getComida())
                .cocinero(comida.getCocinero())
                .cantidadParticipantes(comida.getParticipantes() != null ? comida.getParticipantes().size() : 0)
                .participantes(comida.getParticipantes() != null ? comida.getParticipantes()
                        .stream()
                        .map(ParticipanteMapper :: toResponse)
                        .collect(Collectors.toList())
                        : List.of()
                )
                .build();
    }
}
