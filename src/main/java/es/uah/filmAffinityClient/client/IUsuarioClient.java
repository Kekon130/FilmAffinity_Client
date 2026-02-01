package es.uah.filmAffinityClient.client;

import es.uah.filmAffinityClient.dto.request.user.UserPost;
import es.uah.filmAffinityClient.dto.request.user.UserPut;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/usuarios/users")
public interface IUsuarioClient {
    @GetExchange()
    List<UserResponse> findAll();

    @GetExchange("/id/{id}")
    UserResponse findById(@PathVariable Integer id);

    @GetExchange("/username/{username}")
    UserResponse findByUsername(@PathVariable String username);

    @GetExchange("/email/{email}")
    UserResponse findByEmail(@PathVariable String email);

    @PostExchange()
    UserResponse save(@RequestBody UserPost usuario);

    @PutExchange
    UserResponse update(@RequestBody UserPut usuario);

    @DeleteExchange("/id/{id}")
    void deleteById(@PathVariable Integer id);
}
