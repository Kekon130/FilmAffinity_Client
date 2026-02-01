package es.uah.filmAffinityClient.mapper.user;

import es.uah.filmAffinityClient.dto.request.user.UserForm;
import es.uah.filmAffinityClient.dto.request.user.UserPost;
import es.uah.filmAffinityClient.dto.request.user.UserPut;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {
    @Override
    public UserPost newUser(UserForm userForm) {
        return new UserPost(
                userForm.getUsername(),
                userForm.getEmail(),
                userForm.getPassword(),
                userForm.isEnable()
        );
    }

    @Override
    public UserPut updateUser(UserForm userForm) {
        return new UserPut(
                userForm.getId(),
                userForm.getUsername(),
                userForm.getEmail(),
                userForm.getPassword(),
                userForm.isEnable()
        );
    }

    @Override
    public UserForm toUserForm(UserResponse userResponse) {
        return new UserForm(
                userResponse.getId(),
                userResponse.getUsername(),
                userResponse.getEmail(),
                null,
                userResponse.getEnable()
        );
    }
}
