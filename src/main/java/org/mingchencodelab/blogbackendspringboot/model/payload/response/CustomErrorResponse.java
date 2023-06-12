package org.mingchencodelab.blogbackendspringboot.model.payload.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Constructors, getters, and setters
}

