package org.mingchencodelab.blogbackendspringboot.repository;

import org.mingchencodelab.blogbackendspringboot.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tags", collectionResourceRel = "tags", itemResourceRel = "tags")
public interface TagRepository extends JpaRepository<Tag, Long> {
}
