package com.lucasnovello.comidita_martes.exception;

public class ComidaNotFoundException extends RuntimeException {

    public ComidaNotFoundException(String message) {
        super(message);
    }

    public ComidaNotFoundException(Long id) {
        super("No se encontró la comida con ID: " + id);
    }
}
