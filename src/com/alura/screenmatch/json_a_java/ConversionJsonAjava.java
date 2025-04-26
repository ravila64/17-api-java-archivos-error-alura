package com.alura.screenmatch.json_a_java;

import com.google.gson.Gson;

public class ConversionJsonAjava {
    public static void main(String[] args) {
        String json = """
          {
            "nombre" : "Juan",
            "edad" : 30,
            "email" : "juan@email.com"
          }
        """;

        Gson gson = new Gson();
        Persona persona = gson.fromJson(json, Persona.class);
        System.out.println(persona.getEdad());  //salida es 30
        System.out.println(persona.toString());  // muestra 3 campos
    }
}
