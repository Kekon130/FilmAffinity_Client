package es.uah.filmAffinityClient.service.genero;

import es.uah.filmAffinityClient.model.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGeneroService {
    Page<Genero> findAll(Pageable pageable);
    Genero findById(Integer id);
    Genero getGeneroByNombreIgnoreCase(String nombre);
    Genero save(Genero genero);
    Genero update(Genero genero);
    void deleteById(Integer id);
}
