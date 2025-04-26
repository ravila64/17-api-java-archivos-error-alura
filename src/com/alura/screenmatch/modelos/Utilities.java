package com.alura.screenmatch.modelos;

public class Utilities {
    // verificar si es numero

    public String extraerNumeros(String cadena) {
        StringBuilder numeros = new StringBuilder();
        boolean inserto=false;
        if(cadena==null && !cadena.isEmpty()){
            return "0";
        }
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (Character.isDigit(caracter)) {
                numeros.append(caracter);
                inserto=true;
            }
        }
        if (inserto) return numeros.toString();
        else return "0";
    }
}
