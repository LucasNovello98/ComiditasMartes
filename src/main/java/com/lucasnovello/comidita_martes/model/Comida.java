package com.lucasnovello.comidita_martes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String lugar;
    private String comida;
    private String cocinero;

    @ManyToMany
    @JoinTable(
            name = "comida_participante",
            joinColumns = @JoinColumn(name = "comida_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantes;

}
