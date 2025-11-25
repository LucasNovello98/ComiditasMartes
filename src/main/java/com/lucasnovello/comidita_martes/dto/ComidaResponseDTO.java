package com.lucasnovello.comidita_martes.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComidaResponseDTO {

    private Long id;
    private LocalDate fecha;
    private String lugar;
    private String comida;
    private String cocinero;

    // dato calculado
    private Integer cantidadParticipantes;

    // lista de dto de participante
    private List<ParticipanteResponseDTO> participantes;


}
