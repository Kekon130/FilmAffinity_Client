package es.uah.filmAffinityClient.dto.response.usuario;

import java.util.List;

public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private Boolean enable;
    private List<String> roles;

    public UserResponse(Integer id, String username, String email, Boolean enable, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRolesAsString() {
        return String.join(", ", roles);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                '}';
    }
}
