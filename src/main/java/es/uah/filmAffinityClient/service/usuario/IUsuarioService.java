package es.uah.filmAffinityClient.service.usuario;

import es.uah.filmAffinityClient.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {
    Page<Usuario> findAll(Pageable pageable);
    Usuario findById(Integer id);
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    Usuario login(String email, String password);
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    void deleteById(Integer id);
}
