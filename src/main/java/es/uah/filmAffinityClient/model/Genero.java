package es.uah.filmAffinityClient.model;

import java.util.Set;

public class Genero {
    private Integer id;
    private String nombre;
    private String descripcion;
    Set<Pelicula> peliculas;

    public Genero() {}

    public Genero(Integer id, String nombre, String descripcion, Set<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peliculas = peliculas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", peliculas=" + peliculas +
                '}';
    }
}
