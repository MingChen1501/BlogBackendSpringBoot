package org.mingchencodelab.blogbackendspringboot.repository;

import org.mingchencodelab.blogbackendspringboot.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "comments", collectionResourceRel = "comments", itemResourceRel = "comments")
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
