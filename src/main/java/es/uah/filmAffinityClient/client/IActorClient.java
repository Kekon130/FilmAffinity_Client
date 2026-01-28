package es.uah.filmAffinityClient.client;

import es.uah.filmAffinityClient.model.Actor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/peliculas/actors")
public interface IActorClient {
    @GetExchange
    List<Actor> findAll();

    @GetExchange("/id/{id}")
    Actor findById(@PathVariable("id") Integer id);

    @PostExchange
    Actor save(@RequestBody Actor actor);

    @PutExchange
    Actor update(@RequestBody Actor actor);

    @DeleteExchange("/id/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
