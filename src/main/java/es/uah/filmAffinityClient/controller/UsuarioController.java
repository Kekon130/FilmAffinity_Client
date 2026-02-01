package es.uah.filmAffinityClient.controller;

import es.uah.filmAffinityClient.dto.request.user.UserForm;
import es.uah.filmAffinityClient.dto.request.user.UserPost;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;
import es.uah.filmAffinityClient.mapper.user.IUserMapper;
import es.uah.filmAffinityClient.model.Usuario;
import es.uah.filmAffinityClient.paginator.PageRenderer;
import es.uah.filmAffinityClient.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/uusuarios")
public class UsuarioController {
    private final IUsuarioService usuarioService;
    private final IUserMapper userMapper;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, IUserMapper userMapper) {
        this.usuarioService = usuarioService;
        this.userMapper = userMapper;
    }

    @GetMapping("/new")
    public String newUsuario(Model model) {
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", new UserPost());
        return  "usuario/formUsuario";
    }

    @PostMapping("/save")
    public String saveUsuario(Model model, UserForm usuario, RedirectAttributes attributes) {
        UserResponse nuevoUsuario;
        if (usuario.getId() != null && usuario.getId() > 0) {
            nuevoUsuario = this.usuarioService.update(usuario);
            attributes.addFlashAttribute("msg", "Los datos del usuario fueron actualizados correctamente.");
        } else {
            nuevoUsuario = this.usuarioService.save(usuario);
            attributes.addFlashAttribute("msg", "Los datos del usuario fueron guardados correctamente.");
        }
        model.addAttribute("usuario", nuevoUsuario);
        return "redirect:/uusuarios/view/" + nuevoUsuario.getId();
    }

    @GetMapping("view/{id}")
    public String viewUsuario(Model model, @PathVariable int id) {
        UserResponse usuario;
        if (!model.containsAttribute("usuario")) {
            usuario = this.usuarioService.findById(id);
            model.addAttribute("usuario", usuario);
        } else {
            usuario = (UserResponse) model.getAttribute("usuario");
        }
        model.addAttribute("titulo", "Detalles del usuario " + usuario.getUsername());
        return "usuario/viewUsuario";
    }

    @GetMapping("/list")
    public String usuariosList(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<UserResponse> usuarios = this.usuarioService.findAll(pageable);
        PageRenderer<UserResponse> pageRender = new PageRenderer<>("/uusuarios/list", usuarios);
        model.addAttribute("titulo", "Listado de Usuarios");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("page", pageRender);
        return "usuario/listUsuarios";
    }

    @GetMapping("/edit/{id}")
    public String editUsuario(Model model, @PathVariable int id) {
        UserResponse usuario = this.usuarioService.findById(id);
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("usuario", this.userMapper.toUserForm(usuario));
        return "usuario/formUsuario";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable int id, RedirectAttributes attributes) {
        this.usuarioService.delete(id);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado correctamente.");
        return "redirect:/uusuarios/list";
    }
}
