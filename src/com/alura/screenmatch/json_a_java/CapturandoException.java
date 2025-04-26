package com.alura.screenmatch.json_a_java;

public class CapturandoException {
    public static void main(String[] args) {
        try {
            Persona p = null;
            System.out.println(p.getNombre());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index Out Of Bounds Exception");
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
