package com.cenfotec.examen2.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String autor;
    private String objetivo;
    private String categoria;
    private String keyword;
    private float duracionTotal;

    public Workshop() {
    }

    public Workshop(long id, String nombre, String autor, String objetivo, String categoria, String keyword, float duracionTotal, Set<Tareas> tareas) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.objetivo = objetivo;
        this.categoria = categoria;
        this.keyword = keyword;
        this.duracionTotal = duracionTotal;
        this.tareas = tareas;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="workshop")
    private Set<Tareas> tareas;

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tareas> tareas) {
        this.tareas = tareas;
    }

    public float getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(float duracionTotal) {

        this.duracionTotal = duracionTotal + this.getDuracionTotal();
    }
}
