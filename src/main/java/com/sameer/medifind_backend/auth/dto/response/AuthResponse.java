package com.sameer.medifind_backend.auth.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    private String email;

    private String role;

    private String message;
}