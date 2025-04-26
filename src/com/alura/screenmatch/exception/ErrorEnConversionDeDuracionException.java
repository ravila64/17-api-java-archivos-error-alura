package com.alura.screenmatch.exception;

// aqui se cambia el extends de Throwable a RunTimeException
public class ErrorEnConversionDeDuracionException extends RuntimeException {
    private  String mensaje;
    public ErrorEnConversionDeDuracionException(String str) {
        this.mensaje = str;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
