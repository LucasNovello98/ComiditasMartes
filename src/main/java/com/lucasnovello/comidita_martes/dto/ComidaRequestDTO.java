package com.lucasnovello.comidita_martes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComidaRequestDTO {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotBlank(message = "El lugar es obligatorio")
    private String lugar;

    @NotBlank(message = "La comida es obligatoria")
    private String comida;

    private String cocinero;

    private List<Long> participantesIds;
}
