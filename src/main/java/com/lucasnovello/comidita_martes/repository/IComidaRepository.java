package com.lucasnovello.comidita_martes.repository;

import com.lucasnovello.comidita_martes.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IComidaRepository extends JpaRepository<Comida, Long> {


    Optional<Comida> findByFechaAndLugarAndComida(LocalDate fecha, String lugar, String comidaNombre);
}
