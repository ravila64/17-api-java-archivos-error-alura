package com.alura.screenmatch.files;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LeerFichero {
    public static void main(String[] args) throws IOException {
        File file = new File("peliculas.txt");
        FileReader reader = new FileReader(file);
        int data = reader.read();
        while (data != -1) {
            System.out.print((char) data);
            data = reader.read();
        }
        reader.close();
        System.out.println();
        System.out.println("Lectura del archivo ok");
    }
}
