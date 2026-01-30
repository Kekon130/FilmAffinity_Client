package es.uah.filmAffinityClient.service.pelicula;

import es.uah.filmAffinityClient.client.IPeliculaClient;
import es.uah.filmAffinityClient.model.Pelicula;
import es.uah.filmAffinityClient.paginator.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService implements IPeliculaService {
    private final IPeliculaClient peliculaClient;

    @Autowired
    public PeliculaService(IPeliculaClient peliculaClient) {
        this.peliculaClient = peliculaClient;
    }

    @Override
    public Page<Pelicula> findAll(Pageable pageable) {
        List<Pelicula> peliculas = this.peliculaClient.findAll();
        return PageUtils.toPage(peliculas, pageable);
    }

    @Override
    public Pelicula findById(Integer id) {
        Pelicula pelicula = null;
        if (id != null && id > 0) {
            pelicula = this.peliculaClient.findById(id);
        }
        return pelicula;
    }

    @Override
    public Page<Pelicula> findByTituloIgnoreCase(String titulo, Pageable pageable) {
        Page<Pelicula> peliculas = null;
        if (titulo != null && !titulo.isBlank()) {
            List<Pelicula> list  = this.peliculaClient.findByTituloIgnoreCase(titulo);
            peliculas = PageUtils.toPage(list, pageable);
        }
        return peliculas;
    }

    @Override
    public Page<Pelicula> findByDirectorIgnoreCase(String director, Pageable pageable) {
        Page<Pelicula> peliculas = Page.empty(pageable);
        if (director != null && !director.isBlank()) {
            List<Pelicula> list = this.peliculaClient.findByDirectorContainsIgnoreCase(director);
            peliculas = PageUtils.toPage(list, pageable);
        }
        return peliculas;
    }

    @Override
    public Page<Pelicula> findBySagaIgnoreCase(String saga, Pageable pageable) {
        Page<Pelicula> peliculas = Page.empty(pageable);
        if (saga != null && !saga.isBlank()) {
            List<Pelicula> list = this.peliculaClient.findBySagaIgnoreCase(saga);
            peliculas = PageUtils.toPage(list, pageable);
        }
        return peliculas;
    }

    @Override
    public Page<Pelicula> findByGenerosIgnoreCase(String genero, Pageable pageable) {
        Page<Pelicula> peliculas = Page.empty(pageable);
        if (genero != null && !genero.isBlank()) {
            List<Pelicula> list = this.peliculaClient.findByGenerosNombreIgnoreCase(genero);
            peliculas = PageUtils.toPage(list, pageable);
        }
        return peliculas;
    }

    @Override
    public Page<Pelicula> findByActoresIgnoreCase(String actor, Pageable pageable) {
        Page<Pelicula> peliculas = Page.empty(pageable);
        if (actor != null && !actor.isBlank()) {
            List<Pelicula> list = this.peliculaClient.findByActoresIgnoreCase(actor);
            peliculas = PageUtils.toPage(list, pageable);
        }
        return peliculas;
    }

    @Override
    public Pelicula save(Pelicula pelicula) {
        Pelicula savedPelicula = null;
        if (pelicula != null && (pelicula.getId() == null || pelicula.getId() == 0)) {
            pelicula.setId(null);
            savedPelicula = this.peliculaClient.save(pelicula);
        }
        return savedPelicula;
    }

    @Override
    public Pelicula update(Pelicula pelicula) {
        Pelicula updatedPelicula = null;
        if (pelicula != null && pelicula.getId() != null && pelicula.getId() > 0) {
            updatedPelicula = this.peliculaClient.update(pelicula);
        }
        return updatedPelicula;
    }

    @Override
    public void delete(Integer id) {
        if (id != null && id > 0) {
            this.peliculaClient.deleteById(id);
        }
    }

    @Override
    public void addActor(Integer id, String nombre) {
        if (id != null && nombre != null && !nombre.isBlank()) {
            this.peliculaClient.addActor(id, nombre);
        }
    }

    @Override
    public void deleteActor(Integer id, String nombre) {
        if (id != null && nombre != null && !nombre.isBlank()) {
            this.peliculaClient.deleteActor(id, nombre);
        }
    }

    @Override
    public void addGenero(Integer id, String nombre) {
        if (id != null && nombre != null && !nombre.isBlank()) {
            this.peliculaClient.addGenero(id, nombre);
        }
    }

    @Override
    public void deleteGenero(Integer id, String nombre) {
        if (id != null && nombre != null && !nombre.isBlank()) {
            this.peliculaClient.deleteGenero(id, nombre);
        }
    }
}
