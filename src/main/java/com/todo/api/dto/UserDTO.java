package com.todo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

    private String token;
    private String email;
    private String username;
    private String password;
    private String id;
}
