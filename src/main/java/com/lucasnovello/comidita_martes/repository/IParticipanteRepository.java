package com.lucasnovello.comidita_martes.repository;

import com.lucasnovello.comidita_martes.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IParticipanteRepository extends JpaRepository<Participante, Long> {


    Optional<Participante> findByNombre(String nombreParticipante);
}
