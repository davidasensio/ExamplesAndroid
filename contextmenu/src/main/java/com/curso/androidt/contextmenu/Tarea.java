package com.curso.androidt.contextmenu;

import java.util.Date;

/**
 * Created by androidt on 13/05/2015.
*/
public class Tarea {
    private String titulo;
    private String descripcion;
    private int prioridad;
    private Date fecha;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, int prioridad, Date fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fecha = fecha;
    }

    //Getters & Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
