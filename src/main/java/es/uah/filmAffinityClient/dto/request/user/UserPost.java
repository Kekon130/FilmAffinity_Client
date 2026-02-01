package es.uah.filmAffinityClient.dto.request.user;

public class UserPost {
    private String username;
    private String email;
    private String password;
    private boolean enable;

    public UserPost() {
    }

    public UserPost(String username, String email, String password, boolean enable) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enable = enable;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                '}';
    }
}
