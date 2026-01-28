package es.uah.filmAffinityClient.client;

import es.uah.filmAffinityClient.model.Pelicula;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/peliculas/films")
public interface IPeliculaClient {
    @GetExchange
    List<Pelicula> findAll();

    @GetExchange("/id/{id}")
    Pelicula findById(@PathVariable("id") Integer id);

    @GetExchange("/titulo/{titulo}")
    List<Pelicula> findByTituloIgnoreCase(@PathVariable("titulo") String titulo);

    @GetExchange("/director/{director}")
    List<Pelicula> findByDirectorContainsIgnoreCase(@PathVariable("director") String director);

    @GetExchange("/saga/{saga}")
    List<Pelicula> findBySagaIgnoreCase(@PathVariable("saga") String saga);

    @GetExchange("/genero/{nombre}")
    List<Pelicula> findByGenerosNombreIgnoreCase(@PathVariable("nombre") String nombre);

    @GetExchange("/actor/{actor}")
    List<Pelicula> findByActoresIgnoreCase(@PathVariable("actor") String actor);

    @PostExchange
    Pelicula save(@RequestBody Pelicula pelicula);

    @PutExchange
    Pelicula update(@RequestBody Pelicula pelicula);

    @DeleteExchange("/id/{id}")
    void deleteById(@PathVariable("id") Integer id);

    @GetExchange("/add/actor/{id}/{nombre}")
    void addActor(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre);

    @GetExchange("/delete/actor/{id}/{nombre}")
    public void deleteActor(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre);

    @GetExchange("/add/genero/{id}/{nombre}")
    void addGenero(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre);

    @GetExchange("/delete/genero/{id}/{nombre}")
    void  deleteGenero(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre);
}
