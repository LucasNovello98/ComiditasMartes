package com.lucasnovello.comidita_martes.service;

import com.lucasnovello.comidita_martes.model.Comida;
import com.lucasnovello.comidita_martes.model.Participante;
import com.lucasnovello.comidita_martes.repository.IComidaRepository;
import com.lucasnovello.comidita_martes.repository.IParticipanteRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComidaService implements IComidaService{

    @Autowired
    private IComidaRepository comidaRepository;
    @Autowired
    private IParticipanteRepository participanteRepository;

    @Override
    public List<Comida> getComidas() {
        return comidaRepository.findAll();
    }

    @Override
    public Comida saveComida(Comida comida) {
        return comidaRepository.save(comida);
    }

    @Override
    public void deleteComida(Long id) {
        comidaRepository.deleteById(id);
    }

    @Override
    public Comida findComida(Long id) {
        return comidaRepository.findById(id).orElse(null);
    }

    public Comida addParticipante(Long comidaId, Long participanteId) {
        Comida comida = comidaRepository.findById(comidaId).orElseThrow(() -> new RuntimeException("Comida no encontrada"));
        Participante participante = participanteRepository.findById(participanteId).orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        if (!comida.getParticipantes().contains(participante)) {
            comida.getParticipantes().add(participante);
        }

        return comidaRepository.save(comida);
    }

    @Override
    public Comida removeParticipante(Long comidaId, Long participanteId) {

        // buscar comida por id
        Comida comida = comidaRepository.findById(comidaId).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        // buscar participante por id
        Participante participante = participanteRepository.findById(participanteId).orElseThrow(() -> new RuntimeException("Participante no encontrada"));

        // verificar que la comida tenga al participante y lo remueve si esta
        comida.getParticipantes().remove(participante);

        // guarda los cambios
        return comidaRepository.save(comida);
    }


    // metodo para cargar CSV

    public void cargarDesdeCSV(String rutaArchivo) {

            try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {

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






























