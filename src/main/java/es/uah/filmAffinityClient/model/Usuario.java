package es.uah.filmAffinityClient.model;

import java.util.List;
import java.util.Objects;

public class Usuario {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private boolean enable;
    private List<Rol> roles;
    private List<Critica> criticas;

    public Usuario () {

    }
    public Usuario(Integer id, String username, String password, String email, boolean enable, List<Rol> roles, List<Critica> criticas) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.roles = roles;
        this.criticas = criticas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Critica> getCriticas() {
        return criticas;
    }

    public void setCriticas(List<Critica> criticas) {
        this.criticas = criticas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                '}';
    }
}
