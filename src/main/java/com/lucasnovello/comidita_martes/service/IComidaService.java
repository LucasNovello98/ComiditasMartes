package com.lucasnovello.comidita_martes.service;


import com.lucasnovello.comidita_martes.dto.ComidaRequestDTO;
import com.lucasnovello.comidita_martes.dto.ComidaResponseDTO;
import com.lucasnovello.comidita_martes.model.Participante;
import java.util.List;

public interface IComidaService {

    // traer todas las comidas
    List<ComidaResponseDTO> getComidas();

    // guardar nueva comida
    ComidaResponseDTO createComida(ComidaRequestDTO dto);

    // eliminar comida por id
    void deleteComida(Long id);

    // buscar comida por id
    ComidaResponseDTO findComida(Long id);

    // actualizar comida
    ComidaResponseDTO updateComida(ComidaRequestDTO dto, Long id);

    ComidaResponseDTO addParticipante(Long comidaId, Long participanteId);

    ComidaResponseDTO removeParticipante(Long comidaId, Long participanteId);

    void cargarDesdeCSV(String rutaCSV);
}
