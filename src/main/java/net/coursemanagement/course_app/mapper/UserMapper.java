package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.UserDTO;
import net.coursemanagement.course_app.entity.User;

public class UserMapper {
    public static User mapToUser(UserDTO userDTO){
        User user;
        user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPassword()
        );
        return user;
    }

    public static UserDTO mapToUserDto(User user){
        UserDTO userDto;
        userDto = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
        return userDto;
    }
}
