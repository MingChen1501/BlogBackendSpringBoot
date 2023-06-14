package org.mingchencodelab.blogbackendspringboot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.mingchencodelab.blogbackendspringboot.model.enumeration.Role;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",
            nullable = false)
    private String username;

    @Column(name = "password",
            nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "email",
            nullable = false)
    @Email(message = "Email is not valid")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,
            columnDefinition = "ENUM('AUTHOR', 'READER')")
    private Role role;

    @Column(name = "full_name",
            nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "deleted_at",
            columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Timestamp deletedAt;
}
