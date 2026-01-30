package es.uah.filmAffinityClient.service.usuario;

import es.uah.filmAffinityClient.client.IUsuarioClient;
import es.uah.filmAffinityClient.model.Usuario;
import es.uah.filmAffinityClient.paginator.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    private final IUsuarioClient usuarioClient;

    @Autowired
    public UsuarioService (IUsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        List<Usuario> usuarios = this.usuarioClient.findAll();
        return PageUtils.toPage(usuarios, pageable);
    }

    @Override
    public Usuario findById(Integer id) {
        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = this.usuarioClient.findById(id);
        }
        return usuario;
    }

    @Override
    public Usuario findByUsername(String username) {
        Usuario usuario = null;
        if (username != null && !username.isBlank()) {
            usuario = this.usuarioClient.findByUsername(username);
        }
        return usuario;
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        if (email != null && !email.isBlank()) {
            usuario = this.usuarioClient.findByEmail(email);
        }
        return usuario;
    }

    @Override
    public Usuario login(String email, String password) {
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        Usuario newUsuario = null;
        if (usuario != null && (usuario.getId() == null || usuario.getId() == 0)) {
            usuario.setId(null);
            newUsuario = this.usuarioClient.save(usuario);
        }
        return newUsuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario updatedUsuario = null;
        if (usuario != null && usuario.getId() != null && usuario.getId() > 0) {
            updatedUsuario = this.usuarioClient.update(usuario);
        }
        return updatedUsuario;
    }

    @Override
    public void deleteById(Integer id) {
        if (id != null && id > 0) {
            this.usuarioClient.deleteById(id);
        }
    }
}
