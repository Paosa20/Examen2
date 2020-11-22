package com.cenfotec.examen2.domain;

import javax.persistence.*;

@Entity
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String descripcion;
    private String textoLeido; //A LOS USUARIOS
    private float duracion;

    //tiempo
    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)//EL WORKSHOP
    private Workshop workshop;

    public Tareas() {
    }

    public Tareas(long id, String nombre, String descripcion, String textoLeido, float duracion, Workshop workshop) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.textoLeido = textoLeido;
        this.duracion = duracion;
        this.workshop = workshop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTextoLeido() {
        return textoLeido;
    }

    public void setTextoLeido(String textoLeido) {
        this.textoLeido = textoLeido;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
