package es.uah.filmAffinityClient.controller;

import es.uah.filmAffinityClient.model.Genero;
import es.uah.filmAffinityClient.paginator.PageRenderer;
import es.uah.filmAffinityClient.service.genero.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ggeneros")
public class GeneroController {
    private IGeneroService generoService;

    @Autowired
    public GeneroController(IGeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/new")
    public String newGenero(Model model) {
        model.addAttribute("titulo","Nuevo Género Cinematográfico");
        Genero genero = new Genero();
        model.addAttribute("genero", genero);
        return "genero/formGenero";
    }

    @PostMapping("/save")
    public String saveGenero(Model model, Genero genero, RedirectAttributes attributes) {
            if (genero.getId() != null && genero.getId() > 0) {
            this.generoService.update(genero);
            attributes.addFlashAttribute("msg", "Los datos del género cinematográfico fueron actualizados correctamente.");
        } else {
            this.generoService.save(genero);
            attributes.addFlashAttribute("msg", "Los datos del género cinematográfico fueron guardados correctamente.");
        }
        return "redirect:/ggeneros/list";
    }

    @GetMapping("/list")
    public String generosList(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Genero> generos = this.generoService.findAll(pageable);
        PageRenderer<Genero> pageRenderer = new PageRenderer<Genero>("/ggeneros/list", generos);
        model.addAttribute("titulo", "Listado de todos los Géneros de Películas");
        model.addAttribute("generos", generos);
        model.addAttribute("page", pageRenderer);
        return "genero/listGeneros";
    }

    @GetMapping("/edit/{id}")
    public String editGenero(Model model, @PathVariable("id") Integer id) {
        Genero genero = this.generoService.findById(id);
        model.addAttribute("titulo", "Editar Género Cinematográfico");
        model.addAttribute("genero", genero);
        return "genero/formGenero";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenero(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        this.generoService.deleteById(id);
        attributes.addFlashAttribute("msg", "Los datos del género cinematográfico fueron borrados correctamente");
        return "redirect:/ggeneros/list";
    }
}
