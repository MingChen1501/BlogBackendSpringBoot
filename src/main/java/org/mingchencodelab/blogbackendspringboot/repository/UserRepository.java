package org.mingchencodelab.blogbackendspringboot.repository;

import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //find user by username and return Optional<User>
    Optional<User> findByUsername(String username);

}
