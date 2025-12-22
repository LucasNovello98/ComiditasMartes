package com.lucasnovello.comidita_martes.service;

import com.lucasnovello.comidita_martes.dto.ComidaRequestDTO;
import com.lucasnovello.comidita_martes.dto.ComidaResponseDTO;
import com.lucasnovello.comidita_martes.exception.ComidaNotFoundException;
import com.lucasnovello.comidita_martes.exception.ParticipanteNotFoundException;
import com.lucasnovello.comidita_martes.mapper.ComidaMapper;
import com.lucasnovello.comidita_martes.model.Comida;
import com.lucasnovello.comidita_martes.model.Participante;
import com.lucasnovello.comidita_martes.repository.IComidaRepository;
import com.lucasnovello.comidita_martes.repository.IParticipanteRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComidaService implements IComidaService{

    private final IComidaRepository comidaRepository;
    private final IParticipanteRepository participanteRepository;

    public ComidaService(IComidaRepository comidaRepository,
                         IParticipanteRepository participanteRepository) {
        this.comidaRepository = comidaRepository;
        this.participanteRepository = participanteRepository;
    }

    // traer todas las comidas
    @Override
    public List<ComidaResponseDTO> getComidas() {
        return comidaRepository.findAll()
                .stream()
                .map(ComidaMapper::toResponse)
                .toList();
    }

    // traer una comida por Id
    @Override
    public ComidaResponseDTO findComida(Long id) {
        Comida comida = comidaRepository.findById(id).orElseThrow(() -> new ComidaNotFoundException(id));
        return ComidaMapper.toResponse(comida);
    }

    // crear una comida
    @Override
    public ComidaResponseDTO createComida(ComidaRequestDTO dto) {
        List<Participante> participantes = dto.getParticipantesIds() != null ?
                participanteRepository.findAllById(dto.getParticipantesIds()) : new ArrayList<>();

        Comida comida = ComidaMapper.toEntity(dto, participantes);
        Comida comidaCreada = comidaRepository.save(comida);
        return ComidaMapper.toResponse(comidaCreada);
    }

    // actualizar una comida
    @Override
    public ComidaResponseDTO updateComida(ComidaRequestDTO dto, Long id) {
        Comida comida = comidaRepository.findById(id).orElseThrow(() -> new ComidaNotFoundException(id));

        comida.setFecha(dto.getFecha());
        comida.setLugar(dto.getLugar());
        comida.setComida(dto.getComida());
        comida.setCocinero(dto.getCocinero());

        List<Participante> participantes = dto.getParticipantesIds() != null ? participanteRepository.findAllById(dto.getParticipantesIds())
                : new ArrayList<>();

        comida.setParticipantes(participantes);

        Comida updatedComida = comidaRepository.save(comida);

        return ComidaMapper.toResponse(updatedComida);
    }

    // eliminar una comida
    @Override
    public void deleteComida(Long id) {
        if (!comidaRepository.existsById(id)) {
            throw new ComidaNotFoundException(id);
        }
        comidaRepository.deleteById(id);
    }

    // agregar participante a una comida
    @Override
    public ComidaResponseDTO addParticipante(Long comidaId, Long participanteId) {
        Comida comida = comidaRepository.findById(comidaId).orElseThrow(() -> new ComidaNotFoundException(comidaId));
        Participante participante = participanteRepository.findById(participanteId).orElseThrow(() -> new ParticipanteNotFoundException(participanteId));

        if (!comida.getParticipantes().contains(participante)) {
            comida.getParticipantes().add(participante);
        }

        Comida comidaActualizada = comidaRepository.save(comida);
        return ComidaMapper.toResponse(comidaActualizada);
    }

    // eliminar participante de una comida
    @Override
    public ComidaResponseDTO removeParticipante(Long comidaId, Long participanteId) {
        Comida comida = comidaRepository.findById(comidaId).orElseThrow(() -> new ComidaNotFoundException(comidaId));
        Participante participante = participanteRepository.findById(participanteId).orElseThrow(() -> new ParticipanteNotFoundException(participanteId));

        comida.getParticipantes().remove(participante);

        Comida comidaActualizada = comidaRepository.save(comida);
        return ComidaMapper.toResponse(comidaActualizada);
    }

    // cargar excel
    public void cargarDesdeCSV(String rutaArchivo) {

            try {
                ClassPathResource resource = new ClassPathResource("comidas.csv");
                InputStream inputStream = resource.getInputStream();

                CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

                String[] encabezados = reader.readNext(); // leer la primera fila (nombres)
                if (encabezados == null) return;



                // 0. Crear todos los participantes a partir de los encabezados
                for (int i = 4; i < encabezados.length; i++) {
                    String nombreParticipante = encabezados[i].trim();
                    participanteRepository.findByNombre(nombreParticipante)
                            .orElseGet(() -> {
                                Participante nuevo = new Participante();
                                nuevo.setNombre(nombreParticipante);
                                return participanteRepository.save(nuevo);
                            });
                }

                // 1. Procesar cada fila del CSV
                String[] fila;
                while ((fila = reader.readNext()) != null) {

                    if (fila.length < 4) continue;

                    // 📅 Parsear la fecha
                    String fechaTexto = fila[0].trim();
                    LocalDate fecha;
                    try {
                        if (!fechaTexto.matches(".*/.*/.*")) {
                            int dia = Integer.parseInt(fechaTexto.split("/")[0]);
                            int mes = Integer.parseInt(fechaTexto.split("/")[1]);
                            int anio = LocalDate.now().getYear();
                            fecha = LocalDate.of(anio, mes, dia);
                        } else {
                            fecha = LocalDate.parse(fechaTexto,
                                    java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy"));
                        }
                    } catch (Exception e) {
                        System.err.println("⚠️ Error parseando fecha: " + fechaTexto);
                        continue;
                    }

                    String lugar = fila[1].trim();
                    String comidaNombre = fila[2].trim();
                    String cocinero = fila[3].trim();

                    // 2️⃣ Buscar comida existente o crear nueva
                    Comida comida = comidaRepository
                            .findByFechaAndLugarAndComida(fecha, lugar, comidaNombre)
                            .orElseGet(() -> {
                                Comida nueva = new Comida();
                                nueva.setFecha(fecha);
                                nueva.setLugar(lugar);
                                nueva.setComida(comidaNombre);
                                nueva.setCocinero(cocinero);
                                return nueva;
                            });

                    // 3️⃣ Crear lista de participantes de esta comida
                    List<Participante> participantes = comida.getParticipantes() != null
                            ? new ArrayList<>(comida.getParticipantes())
                            : new ArrayList<>();

                    for (int i = 4; i < fila.length; i++) {
                        String nombreParticipante = encabezados[i].trim();
                        String valor = fila[i].trim().toLowerCase();

                        if (valor.equals("true")) {
                            // buscar participante existente
                            Participante participante = participanteRepository
                                    .findByNombre(nombreParticipante)
                                    .orElseThrow(() -> new RuntimeException("Participante no encontrado: " + nombreParticipante));

                            if (!participantes.contains(participante)) {
                                participantes.add(participante);
                            }
                        }
                    }

                    // 4️⃣ Asociar participantes a la comida
                    comida.setParticipantes(participantes);

                    // 5️⃣ Guardar comida con relaciones
                    comidaRepository.save(comida);

                    System.out.println("✅ Procesada comida: " + comidaNombre + " (" + fecha + ")");
                }

                System.out.println("✅ Todos los datos del CSV cargados y participantes creados correctamente.");

            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
                System.err.println("❌ Error leyendo el archivo CSV.");
            }
        }
}






























