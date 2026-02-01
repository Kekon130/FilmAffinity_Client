package es.uah.filmAffinityClient.service.usuario;

import es.uah.filmAffinityClient.client.IUsuarioClient;
import es.uah.filmAffinityClient.dto.request.user.UserForm;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;
import es.uah.filmAffinityClient.mapper.user.IUserMapper;
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
    private final IUserMapper userMapper;

    @Autowired
    public UsuarioService (IUsuarioClient usuarioClient, IUserMapper userMapper) {
        this.usuarioClient = usuarioClient;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        List<UserResponse> usuarios = this.usuarioClient.findAll();
        return PageUtils.toPage(usuarios, pageable);
    }

    @Override
    public UserResponse findById(Integer id) {
        UserResponse usuario = null;
        if (id != null && id > 0) {
            usuario = this.usuarioClient.findById(id);
        }
        return usuario;
    }

    @Override
    public UserResponse findByUsername(String username) {
        UserResponse usuario = null;
        if (username != null && !username.isBlank()) {
            usuario = this.usuarioClient.findByUsername(username);
        }
        return usuario;
    }

    @Override
    public UserResponse findByEmail(String email) {
        UserResponse usuario = null;
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
    public UserResponse save(UserForm usuario) {
        UserResponse newUsuario = null;
        if (usuario != null) {
            newUsuario = this.usuarioClient.save(this.userMapper.newUser(usuario));
        }
        return newUsuario;
    }

    @Override
    public UserResponse update(UserForm usuario) {
        UserResponse updatedUsuario = null;
        if (usuario != null && usuario.getId() != null && usuario.getId() > 0) {
            updatedUsuario = this.usuarioClient.update(this.userMapper.updateUser(usuario));
        }
        return updatedUsuario;
    }

    @Override
    public void delete(Integer id) {
        if (id != null && id > 0) {
            this.usuarioClient.deleteById(id);
        }
    }
}
