package es.uah.filmAffinityClient.service.actor;

import es.uah.filmAffinityClient.client.IActorClient;
import es.uah.filmAffinityClient.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActorService implements IActorService {
    private IActorClient actorClient;

    @Autowired
    public ActorService(IActorClient actorClient) {
        this.actorClient = actorClient;
    }

    @Override
    public Page<Actor> findAll(Pageable pageable) {
        List<Actor> actores = this.actorClient.findAll();
        return this.toPage(actores, pageable);
    }

    @Override
    public Actor findById(Integer id) {
        Actor actor = null;
        if (id != null && id > 0) {
            actor = this.actorClient.findById(id);
        }
        return actor;
    }

    @Override
    public Actor save(Actor actor) {
        Actor savedActor = null;
        if (actor != null && (actor.getId() == null || actor.getId() == 0)) {
            actor.setId(null);
            savedActor = this.actorClient.save(actor);
        }
        return savedActor;
    }

    @Override
    public Actor update(Actor actor) {
        Actor updatedActor = null;
        if (actor != null && actor.getId() != null && actor.getId() > 0) {
            updatedActor = this.actorClient.update(actor);
        }
        return updatedActor;
    }

    @Override
    public void deleteById(Integer id) {
        if (id != null && id > 0) {
            this.actorClient.deleteById(id);
        }
    }

    private Page<Actor> toPage(List<Actor> actores, Pageable pageable) {
        if (actores == null || actores.isEmpty()) {
            return Page.empty(pageable);
        }

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startItem = pageSize * pageNumber;

        List<Actor> subList;
        if (actores.size() <= startItem) {
            subList = Collections.emptyList();
        } else {
            int index = Math.min(startItem + pageSize, actores.size());
            subList = actores.subList(startItem, index);
        }

        return new PageImpl<>(subList, PageRequest.of(pageNumber, pageSize), actores.size());
    }
}
