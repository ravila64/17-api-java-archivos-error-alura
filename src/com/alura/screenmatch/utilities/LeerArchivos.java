package com.alura.screenmatch.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerArchivos {
    public boolean leerFichero(String file) {
        try {
            File archivo = new File(file);
            Scanner scanner = new Scanner(archivo);
            System.out.println("Mostrar contenido del fichero "+file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + file + " No encontrado! " + e.getMessage());
            return false;
        }
    }
}
