package com.sameer.medifind_backend.auth.service;

import com.sameer.medifind_backend.auth.dto.request.LoginRequest;
import com.sameer.medifind_backend.auth.dto.request.RegisterRequest;
import com.sameer.medifind_backend.auth.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
