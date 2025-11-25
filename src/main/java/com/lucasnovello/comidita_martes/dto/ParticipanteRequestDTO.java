package com.lucasnovello.comidita_martes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
