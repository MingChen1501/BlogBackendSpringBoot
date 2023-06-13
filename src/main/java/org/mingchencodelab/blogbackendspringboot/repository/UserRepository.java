package org.mingchencodelab.blogbackendspringboot.repository;

import jakarta.validation.constraints.NotNull;
import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    //find user by username and return Optional<User>
    @RestResource(path = "findByUsername", rel = "findByUsername")
    Optional<User> findByUsername(@Param("username") String username);
    boolean existsByUsername(String username);

    //find user by id and return Optional<User>
    @RestResource(path = "findById", rel = "findById")
    @NotNull
    Optional<User> findById(@Param("id") @NotNull Long id);


}
