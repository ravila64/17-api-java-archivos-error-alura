package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOMDb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

// Api-archivos-errores
public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        //captura pelicula a buscar
        Scanner leer = new Scanner(System.in);
        String apiPeliculas=System.getenv("API_KEY_MOVIES");
        if (apiPeliculas != null && !apiPeliculas.isEmpty()) {
            System.out.println("API Key: Verified");
        } else {
            System.out.println("La variable de entorno no está definida, para este dispositivo.");
            return;
        }
        System.out.print("Nombre pelicula  a buscar ");
        var busqueda = leer.nextLine();

        String url = "http://www.omdbapi.com/?t="+busqueda+"&apikey="+apiPeliculas;

        System.out.println("Request API ....");
        String json=null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        try {
            json= response.body();
            System.out.println("Response: "+ json);
        } catch (RuntimeException e) {
            //throw new RuntimeException(e);
            System.out.println("Exception "+e.getMessage());
        }

        //Gson gson = new Gson();
        // maneja una politica de Camel_Case, example title, viene de la API, "Title", year viene con "Year"
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        TituloOMDb miTituloOMDb = gson.fromJson(json, TituloOMDb.class);
        System.out.println(miTituloOMDb);   // asume que se imprime miTituloOMDb.toString()
        Titulo miTitulo = new Titulo(miTituloOMDb);
        System.out.println(miTitulo.toString());

    }
}
