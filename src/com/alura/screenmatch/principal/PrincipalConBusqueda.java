package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOMDb;
import com.google.gson.Gson;

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
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json= response.body();
        System.out.println("Response "+ json);

        Gson gson = new Gson();
        //Titulo miTitulo = gson.fromJson(json, Titulo.class);
        TituloOMDb miTituloOMDb = gson.fromJson(json, TituloOMDb.class);
        System.out.println(miTituloOMDb);
    }
}
