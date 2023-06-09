package org.mingchencodelab.blogbackendspringboot.service;

import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

    //method to delete user by id
    void deleteUser(Long id);

    //method to get user by username
    Optional<User> getUserByUsername(String username);
}
