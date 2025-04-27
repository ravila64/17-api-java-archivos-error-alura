package com.alura.screenmatch.principal;

import com.alura.screenmatch.exception.ErrorEnConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOMDb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
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
        String apiPeliculas = System.getenv("API_KEY_MOVIES");
        if (apiPeliculas != null && !apiPeliculas.isEmpty()) {
            System.out.println("API Key: Verified");
        } else {
            System.out.println("La variable de entorno no está definida, para este dispositivo.");
            return;
        }
        System.out.print("Nombre pelicula  a buscar ");
        var busqueda = leer.nextLine();
        // Convertir blancos a simbolos + en la busqueda
        busqueda = busqueda.replace(" ","+");
        String url = "http://www.omdbapi.com/?t=" + busqueda + "&apikey=" + apiPeliculas;
        System.out.println("Request API ....");
        String json = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
            System.out.println("Response: " + json);
            //Gson gson = new Gson();
            // maneja una politica de Camel_Case, example title, viene de la API, "Title", year viene con "Year"
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            TituloOMDb miTituloOMDb = gson.fromJson(json, TituloOMDb.class);
            System.out.println(miTituloOMDb);   // asume que se imprime miTituloOMDb.toString()
            Titulo miTitulo = new Titulo(miTituloOMDb);
            System.out.println("Titulo ya convertido " + miTitulo.toString());
            // grabacion de datos en un archivo
            FileWriter escritura = new FileWriter("peliculas.txt");
            escritura.write(miTitulo.toString());
            System.out.println("Grabacion ok en archivo : peliculas.txt");
            escritura.close();

        } catch (NumberFormatException e) {
            System.out.println("Ocurrio un error " + e.getMessage());
        } catch (IllegalArgumentException  | NullPointerException e) {
            System.out.println("Error en un argumento de la URL o variable no instanciada");
        }catch (ErrorEnConversionDeDuracionException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finalizo del programa");
        }
    }
}
