package org.mingchencodelab.blogbackendspringboot.service;

import org.mingchencodelab.blogbackendspringboot.model.dto.UserDto;
import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    //method to get all users
    Page<User> getAllUsers(Pageable pageable);

    //method to get user by id
    Optional<User> getUserById(Long id);

    /**
     * @param user the user to create
     * @return Optional<User>
     */
    //method to create user
    Optional<UserDto> createUser(UserDto user) throws Exception;

    //method to update user by id
    Optional<User> updateUser(Long id, User user);

    /**
     * @param id the id of the user to delete
     * @return boolean if the user is deleted
     */
    //method to delete user by id
    boolean deleteUserById(Long id);

    //method to get user by username
    Optional<User> getUserByUsername(String username);
}
