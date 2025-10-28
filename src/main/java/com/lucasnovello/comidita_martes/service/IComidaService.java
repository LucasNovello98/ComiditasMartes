package com.lucasnovello.comidita_martes.service;


import com.lucasnovello.comidita_martes.model.Comida;

import java.util.List;

public interface IComidaService {

    // traer todas las comidas
    public List<Comida> getComidas();

    // guardar nueva comida
    public Comida saveComida(Comida comida);

    // eliminar comida por id
    public void deleteComida(Long id);

    // buscar comida por id
    public Comida findComida(Long id);

    public Comida addParticipante(Long comidaID, Long participanteId);

    public Comida removeParticipante(Long comidaId, Long participanteId);

    void cargarDesdeCSV(String rutaCSV);
}
