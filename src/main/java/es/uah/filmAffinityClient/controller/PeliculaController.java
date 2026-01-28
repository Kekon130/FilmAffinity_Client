package es.uah.filmAffinityClient.controller;

import es.uah.filmAffinityClient.model.Pelicula;
import es.uah.filmAffinityClient.paginator.PageRenderer;
import es.uah.filmAffinityClient.service.pelicula.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ppeliculas")
public class PeliculaController {
    private IPeliculaService peliculaService;

    @Autowired
    public PeliculaController(IPeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/new")
    public String newPelicula(Model model) {
        model.addAttribute("titulo", "Nueva Película");
        model.addAttribute("pelicula", new Pelicula());
        return  "pelicula/formPelicula";
    }

    @PostMapping("/save")
    public String savePelicula(Model model, Pelicula pelicula, RedirectAttributes attributes) {
        if (pelicula.getId() != null && pelicula.getId() > 0) {
            this.peliculaService.update(pelicula);
            attributes.addFlashAttribute("msg", "Los datos de la película fueron actualizados correctamente.");
        } else {
            this.peliculaService.save(pelicula);
            attributes.addFlashAttribute("msg", "Los datos de la película fueron guardados correctamente.");
        }
        return "redirect:/ppeliculas/list";
    }

    @GetMapping("/view/{id}")
    public String viewPelicula(Model model, @PathVariable("id") int id) {
        Pelicula pelicula = this.peliculaService.findById(id);
        model.addAttribute("titulo", "Detalles de la película " + pelicula.getTitulo());
        model.addAttribute("pelicula", pelicula);
        return "pelicula/viewPelicula";
    }

    @GetMapping("/filter")
    public String filterPeliculas(Model model) {
        return  "pelicula/filterPeliculas";
    }

    @GetMapping("/list")
    public String peliculasList(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = this.peliculaService.findAll(pageable);
        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de Películas");
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/list/titulo")
    public String peliculasListTitulo(Model model, @RequestParam("titulo") String titulo, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = null;

        if (titulo == null || titulo.isBlank()) {
            peliculas = this.peliculaService.findAll(pageable);
        } else {
            peliculas = this.peliculaService.findByTituloIgnoreCase(titulo, pageable);
        }

        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de peliculas con titulo " + titulo);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/list/director")
    public String director(Model model, @RequestParam("director") String director, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = null;

        if (director == null || director.isBlank()) {
            peliculas = this.peliculaService.findAll(pageable);
        } else {
            peliculas = this.peliculaService.findByDirectorIgnoreCase(director, pageable);
        }

        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de peliculas con director " + director);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/list/saga")
    public  String saga(Model model, @RequestParam("saga") String saga, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = null;

        if (saga == null || saga.isBlank()) {
            peliculas = this.peliculaService.findAll(pageable);
        } else {
            peliculas = this.peliculaService.findBySagaIgnoreCase(saga, pageable);
        }

        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de peliculas con saga " + saga);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/list/genero/{nombre}")
    public String generos(Model model, @PathVariable("nombre") String nombre, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = null;

        if (nombre == null || nombre.isBlank()) {
            peliculas = this.peliculaService.findAll(pageable);
        } else {
            peliculas = this.peliculaService.findByGenerosIgnoreCase(nombre, pageable);
        }

        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de peliculas del genero " + nombre);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/list/actor/{nombre}")
    public String actores(Model model, @PathVariable("nombre") String nombre, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculas = null;

        if (nombre == null || nombre.isBlank()) {
            peliculas = this.peliculaService.findAll(pageable);
        } else {
            peliculas = this.peliculaService.findByActoresIgnoreCase(nombre, pageable);
        }

        PageRenderer<Pelicula> pageRenderer = new PageRenderer<>("/ppeliculas/list", peliculas);
        model.addAttribute("titulo", "Listado de peliculas del actor " + nombre);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("page", pageRenderer);
        return "pelicula/listPeliculas";
    }

    @GetMapping("/edit/{id}")
    public String editPelicula(Model model, @PathVariable("id") Integer id) {
        Pelicula pelicula = this.peliculaService.findById(id);
        model.addAttribute("titulo", "Editar Película");
        model.addAttribute("pelicula", pelicula);
        return "pelicula/formPelicula";
    }

    @GetMapping("/delete/{id}")
    public String deletePelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        this.peliculaService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Los datos de la película se han borrado correctamente.");
        return "redirect:/ppeliculas/list";
    }

    @PostMapping("/actor/add")
    public String addActor(@RequestParam Integer id, @RequestParam String nombre, RedirectAttributes attributes) {
        this.peliculaService.addActor(id, nombre);
        attributes.addFlashAttribute("msg", "El actor ha sido añadido correctamente.");
        return "redirect:/ppeliculas/view/" + id;
    }

    @GetMapping("/delete/{id}/actor/{nombre}")
    public String deleteActor(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre, RedirectAttributes attributes) {
        this.peliculaService.deleteActor(id, nombre);
        attributes.addFlashAttribute("msg", "El actor ha sido borrado correctamente.");
        return "redirect:/ppeliculas/view/" + id;
    }

    @PostMapping("/genero/add")
    public String addGenero(@RequestParam Integer id, @RequestParam String nombre, RedirectAttributes attributes) {
        this.peliculaService.addGenero(id, nombre);
        attributes.addFlashAttribute("msg", "El género cinematográfico ha sido añadido correctamente.");
        return "redirect:/ppeliculas/view/" + id;
    }

    @GetMapping("/delete/{id}/genero/{nombre}")
    public String deleteGenero(@PathVariable("id") Integer id, @PathVariable("nombre") String nombre, RedirectAttributes attributes) {
        this.peliculaService.deleteGenero(id, nombre);
        attributes.addFlashAttribute("msg", "El género cinematográfico ha sido eliminado correctamente.");
        return  "redirect:/ppeliculas/view/" + id;
    }
}
