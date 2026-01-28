package es.uah.filmAffinityClient.service.pelicula;

import es.uah.filmAffinityClient.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPeliculaService {
    Page<Pelicula> findAll(Pageable pageable);
    Pelicula findById(Integer id);
    Page<Pelicula> findByTituloIgnoreCase(String titulo, Pageable pageable);
    Page<Pelicula> findByDirectorIgnoreCase(String director, Pageable pageable);
    Page<Pelicula> findBySagaIgnoreCase(String saga, Pageable pageable);
    Page<Pelicula> findByGenerosIgnoreCase(String genero, Pageable pageable);
    Page<Pelicula> findByActoresIgnoreCase(String actor, Pageable pageable);
    Pelicula save(Pelicula pelicula);
    Pelicula update(Pelicula pelicula);
    void delete(Integer id);
    void addActor(Integer id, String nombre);
    void deleteActor(Integer id, String nombre);
    void addGenero(Integer id, String nombre);
    void deleteGenero(Integer id, String nombre);
}
