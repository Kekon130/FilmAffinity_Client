package es.uah.filmAffinityClient.client;

import es.uah.filmAffinityClient.model.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/usuarios/users")
public interface IUsuarioClient {
    @GetExchange()
    List<Usuario> findAll();

    @GetExchange("/id/{id}")
    Usuario findById(@PathVariable Integer id);

    @GetExchange("/username/{username}")
    Usuario findByUsername(@PathVariable String username);

    @GetExchange("/email/{email}")
    Usuario findByEmail(@PathVariable String email);

    @PostExchange()
    Usuario save(@RequestBody Usuario usuario);

    @PutExchange()
    Usuario update(@RequestBody Usuario usuario);

    @DeleteExchange("/id/{id}")
    void deleteById(@PathVariable Integer id);
}
