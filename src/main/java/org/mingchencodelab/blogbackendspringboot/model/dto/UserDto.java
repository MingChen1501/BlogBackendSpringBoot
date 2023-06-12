package org.mingchencodelab.blogbackendspringboot.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.mingchencodelab.blogbackendspringboot.model.enumeration.Role;

@Builder
@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Role is required")
    private Role role;
    @NotBlank(message = "Full name is required")
    @Size(max = 50, message = "Full name must not exceed 50 characters")
    private String fullName;

    /**
     * @param user the user to convert
     * @return UserDto the converted userDto
     */
    //convert from User to UserDto
    public static UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .fullName(user.getFullName())
                .build();
    }

    /**
     * @param userDto the userDto to convert
     * @return User the converted user
     */
    //convert from UserDto to User
    public static User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .fullName(userDto.getFullName())
                .build();
    }
}
