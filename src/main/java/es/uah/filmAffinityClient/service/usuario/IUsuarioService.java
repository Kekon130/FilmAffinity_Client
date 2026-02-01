package es.uah.filmAffinityClient.service.usuario;

import es.uah.filmAffinityClient.dto.request.user.UserForm;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;
import es.uah.filmAffinityClient.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse findById(Integer id);
    UserResponse findByUsername(String username);
    UserResponse findByEmail(String email);
    Usuario login(String email, String password);
    UserResponse save(UserForm usuario);
    UserResponse update(UserForm usuario);
    void delete(Integer id);
}
