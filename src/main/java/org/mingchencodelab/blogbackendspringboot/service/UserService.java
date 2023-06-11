package org.mingchencodelab.blogbackendspringboot.service;

import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    //method to get all users
    Page<User> getAllUsers(Pageable pageable);

    //method to get user by id
    Optional<User> getUserById(Long id);

    //method to create user
    Optional<User> createUser(User user);

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
