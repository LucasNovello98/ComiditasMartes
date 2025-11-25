package com.lucasnovello.comidita_martes.service;


import com.lucasnovello.comidita_martes.dto.ParticipanteRequestDTO;
import com.lucasnovello.comidita_martes.dto.ParticipanteResponseDTO;
import com.lucasnovello.comidita_martes.mapper.ParticipanteMapper;
import com.lucasnovello.comidita_martes.model.Participante;
import com.lucasnovello.comidita_martes.repository.IParticipanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipanteService implements IParticipanteService{

    private final IParticipanteRepository participanteRepository;

    public ParticipanteService(IParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }


    @Override
    public List<ParticipanteResponseDTO> getParticipantes() {
        return  participanteRepository.findAll()
                .stream()
                .map(ParticipanteMapper::toResponse)
                .toList();
    }

    @Override
    public ParticipanteResponseDTO findParticipante(Long id) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));
        return ParticipanteMapper.toResponse(participante);
    }

    @Override
    public ParticipanteResponseDTO createParticipante(ParticipanteRequestDTO participanteDto) {
        Participante participante = ParticipanteMapper.toEntity(participanteDto);
        Participante participanteCreado = participanteRepository.save(participante);
        return ParticipanteMapper.toResponse(participanteCreado);
    }

    @Override
    public void deleteParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new RuntimeException("No se encontro el participate");
        }
        participanteRepository.deleteById(id);
    }

    @Override
    public ParticipanteResponseDTO updateParticipante(ParticipanteRequestDTO participanteDto, Long id) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        participante.setNombre(participanteDto.getNombre());

        Participante participanteActualizado = participanteRepository.save(participante);
        return ParticipanteMapper.toResponse(participanteActualizado);
    }
}
