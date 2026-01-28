package es.uah.filmAffinityClient.client;

import es.uah.filmAffinityClient.model.Genero;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/peliculas/genres")
public interface IGeneroClient {
    @GetExchange
    List<Genero> findAll();

    @GetExchange("/id/{id}")
    Genero findById(@PathVariable("id") Integer id);

    @GetExchange("/nombre/{nombre}")
    Genero findByNombre(@PathVariable("nombre") String nombre);

    @PostExchange
    Genero save(@RequestBody Genero genero);

    @PutExchange
    Genero update(@RequestBody Genero genero);

    @DeleteExchange("/id/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
