package es.uah.filmAffinityClient.mapper.user;

import es.uah.filmAffinityClient.dto.request.user.UserForm;
import es.uah.filmAffinityClient.dto.request.user.UserPost;
import es.uah.filmAffinityClient.dto.request.user.UserPut;
import es.uah.filmAffinityClient.dto.response.usuario.UserResponse;

public interface IUserMapper {
    UserPost newUser(UserForm userForm);
    UserPut updateUser(UserForm userForm);
    UserForm toUserForm(UserResponse userResponse);
}
