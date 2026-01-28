package es.uah.filmAffinityClient.controller;

import es.uah.filmAffinityClient.model.Actor;
import es.uah.filmAffinityClient.paginator.PageRenderer;
import es.uah.filmAffinityClient.service.actor.IActorService;
import es.uah.filmAffinityClient.service.datetime.IDateTimeFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/aactores")
public class ActorController {
    private IActorService actorService;
    private IDateTimeFormater dateTimeFormatter;

    @Autowired
    public ActorController(IActorService actorService, IDateTimeFormater dateTimeFormatter) {
        this.actorService = actorService;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @GetMapping("/new")
    public String newActor(Model model) {
        model.addAttribute("titulo", "Nuevo Actor");
        model.addAttribute("actor", new Actor());
        return "actor/formActor";
    }

    @PostMapping("/save")
    public String saveActor(Model model, Actor actor, RedirectAttributes attributes) {
        if (actor.getId() != null && actor.getId() > 0) {
            this.actorService.update(actor);
            attributes.addFlashAttribute("msg", "La información del actor se ha actualizado correctamente.");
        } else {
            this.actorService.save(actor);
            attributes.addFlashAttribute("msg", "Los datos del actor se guardaron correctamente.");
        }
        return "redirect:/aactores/list";
    }

    @GetMapping("/list")
    public String actoresList(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
            Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> actores = this.actorService.findAll(pageable);
        PageRenderer<Actor> pageRenderer = new PageRenderer<>("/aactores/list", actores);
        model.addAttribute("titulo", "Listado de actores");
        model.addAttribute("actores", actores);
        model.addAttribute("dateTimeFormatter", this.dateTimeFormatter);
        model.addAttribute("page", pageRenderer);
        return "actor/listActores";
    }

    @GetMapping("/edit/{id}")
    public String editActor(Model model, @PathVariable("id") Integer id) {
        Actor actor = this.actorService.findById(id);
        model.addAttribute("titulo", "Editar Actor");
        model.addAttribute("actor", actor);
        return "actor/formActor";
    }

    @GetMapping("/delete/{id}")
    public String deleteActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        this.actorService.deleteById(id);
        attributes.addFlashAttribute("msg", "Los datos del actor se ha eliminado correctamente");
        return "redirect:/aactores/list";
    }
}
