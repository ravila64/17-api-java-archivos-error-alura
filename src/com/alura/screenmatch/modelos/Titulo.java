package com.alura.screenmatch.modelos;

import com.alura.screenmatch.exception.ErrorEnConversionDeDuracionException;
import com.google.gson.annotations.SerializedName;
import jdk.jshell.execution.Util;

public class Titulo implements Comparable<Titulo> {
    @SerializedName("Title")
    private String nombre;
    @SerializedName("Year")
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    @SerializedName("Runtime")  // ojo esta String y necesito int, fix?
    private int duracionEnMinutos;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOMDb miTituloOMDb) {
        Utilities util = new Utilities();
        this.nombre = miTituloOMDb.title();
        String fechaL = miTituloOMDb.year();
        String fechaLanza = "0";
        if (fechaL.contains("N/A")) {
            throw new ErrorEnConversionDeDuracionException("la Fecha contiene un N/A, no se puede convertir");
        }else{
            fechaLanza = util.extraerNumeros(fechaL);
        }
        this.fechaDeLanzamiento = Integer.valueOf(fechaLanza);

        String numMinutos ="0";
        String enMinutos = miTituloOMDb.runtime();
        if (enMinutos.contains("N/A")) {
            throw new ErrorEnConversionDeDuracionException("Duracion en minutos contiene un N/A, no se puede convertir");
        }else{
           numMinutos = util.extraerNumeros(enMinutos);
        }
        this.duracionEnMinutos = Integer.valueOf(numMinutos);
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica() {
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota) {
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones() {
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "nombre='" + nombre + '\'' +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", duracionEnMinutos=" +duracionEnMinutos+
                '}';
    }
}
