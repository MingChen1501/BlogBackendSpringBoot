package org.mingchencodelab.blogbackendspringboot.repository;

import jakarta.validation.constraints.NotNull;
import org.mingchencodelab.blogbackendspringboot.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

@RepositoryRestResource(path = "posts", collectionResourceRel = "posts", itemResourceRel = "post")
public interface PostRepository extends JpaRepository<Post, Long> {
    @RestResource(path = "findAuthorById", rel = "findAuthorById")
    Optional<Post> findAuthorById(@NotNull @Param("id") Long id);
}

