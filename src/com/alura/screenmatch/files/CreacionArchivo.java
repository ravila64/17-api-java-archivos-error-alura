package com.alura.screenmatch.files;
import java.io.FileWriter;

public class CreacionArchivo {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("archivo");
            writer.write("Hello");
            writer.close();
            writer.write(" World!");  // saldra error porque ya esta cerrado
            writer.close(); // no se debe cerrar algo ya cerrado, genrea error
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());  // Stream closed
        }
    }
}
