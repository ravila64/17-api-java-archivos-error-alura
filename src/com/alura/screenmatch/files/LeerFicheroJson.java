package com.alura.screenmatch.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerFicheroJson {
    public static void main(String[] args) {
        try {
            File archivo = new File("colores.json");
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado!");
        }
    }
}
