package com.sameer.medifind_backend.auth.service.impl;

import com.sameer.medifind_backend.auth.dto.request.LoginRequest;
import com.sameer.medifind_backend.auth.dto.request.RegisterRequest;
import com.sameer.medifind_backend.auth.dto.response.AuthResponse;
import com.sameer.medifind_backend.auth.service.AuthService;
import com.sameer.medifind_backend.user.entity.User;
import com.sameer.medifind_backend.user.enums.AccountStatus;
import com.sameer.medifind_backend.user.enums.Role;
import com.sameer.medifind_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.ROLE_CUSTOMER)
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Registration Successful")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        return null;

    }
}