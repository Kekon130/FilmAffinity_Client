package es.uah.filmAffinityClient.service.genero;

import es.uah.filmAffinityClient.client.IGeneroClient;
import es.uah.filmAffinityClient.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GeneroService implements IGeneroService {
    private final IGeneroClient generoClient;

    @Autowired
    public GeneroService(IGeneroClient generoClient) {
        this.generoClient = generoClient;
    }

    @Override
    public Page<Genero> findAll(Pageable pageable) {
        List<Genero> generos = this.generoClient.findAll();
        return this.toPage(generos, pageable);
    }

    @Override
    public Genero findById(Integer id) {
        Genero genero = null;
        if (id != null && id > 0) {
            genero = this.generoClient.findById(id);
        }
        return genero;
    }

    @Override
    public Genero getGeneroByNombreIgnoreCase(String nombre) {
        Genero genero = null;
        if (nombre != null && !nombre.isEmpty()) {
            genero = this.generoClient.findByNombre(nombre);
        }
        return genero;
    }

    @Override
    public Genero save(Genero genero) {
        Genero savedGenero = null;
        if (genero != null && (genero.getId() == null || genero.getId() == 0)) {
            genero.setId(0);
            savedGenero = this.generoClient.save(genero);
        }
        return savedGenero;
    }

    @Override
    public Genero update(Genero genero) {
        Genero updatedGenero = null;
        if (genero != null && genero.getId() != null && genero.getId() > 0) {
            updatedGenero = this.generoClient.update(genero);
        }
        return updatedGenero;
    }

    @Override
    public void deleteById(Integer id) {
        if (id != null && id > 0) {
            this.generoClient.deleteById(id);
        }
    }

    private Page<Genero> toPage(List<Genero> generos, Pageable pageable) {
        if (generos == null || generos.isEmpty()) {
            return Page.empty(pageable);
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Genero> subList;
        if (generos.size() <= startItem) {
            subList = Collections.emptyList();
        } else {
            int index = Math.min(startItem + pageSize, generos.size());
            subList = generos.subList(startItem, index);
        }

        return new PageImpl<>(subList, PageRequest.of(currentPage, pageSize), generos.size());
    }
}
