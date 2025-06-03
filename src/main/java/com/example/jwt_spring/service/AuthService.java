package com.example.jwt_spring.service;

import com.example.jwt_spring.dto.AuthRequest;
import com.example.jwt_spring.dto.AuthResponse;
import com.example.jwt_spring.dto.RegisterRequest;
import com.example.jwt_spring.model.User;
import com.example.jwt_spring.repository.UserRepository;
import com.example.jwt_spring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterRequest request) {
    var user = User.builder()
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
        .build();

    userRepository.save(user);
    String token = jwtService.generateToken(user);

    return AuthResponse.builder().token(token).build();
  }

  public AuthResponse login(AuthRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );

    var user = userRepository.findByEmail(request.getEmail())
      .orElseThrow();

    String token = jwtService.generateToken(user);

    return AuthResponse.builder().token(token).build();
  }

}
