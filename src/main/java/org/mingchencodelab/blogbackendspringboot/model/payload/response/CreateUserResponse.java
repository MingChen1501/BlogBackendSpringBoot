package org.mingchencodelab.blogbackendspringboot.model.payload.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponse {
    private String username;
    private String email;
    private String fullName;
    private String role;
}
