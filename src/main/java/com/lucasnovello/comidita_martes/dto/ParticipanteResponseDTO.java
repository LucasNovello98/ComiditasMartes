package com.lucasnovello.comidita_martes.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteResponseDTO {

    private Long id;
    private String nombre;
    private Integer cantidadAsistencias;

}
