package es.uah.filmAffinityClient.service.actor;

import es.uah.filmAffinityClient.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActorService {
    Page<Actor> findAll(Pageable pageable);
    Actor findById(Integer id);
    Actor save(Actor actor);
    Actor update(Actor actor);
    void deleteById(Integer id);
}
