package com.lucasnovello.comidita_martes.exception;

public class ParticipanteNotFoundException extends RuntimeException {

    public ParticipanteNotFoundException(String message) {
        super(message);
    }

    public ParticipanteNotFoundException(Long id) {
        super("No se encontró el participante con ID: " + id);
    }
}
