package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.dto.LoginRequest;
import com.Vicvin.Bookflix.dto.SignupRequest;
import com.Vicvin.Bookflix.entity.Role;
import com.Vicvin.Bookflix.entity.User;
import com.Vicvin.Bookflix.repository.UserRepository;
import com.Vicvin.Bookflix.security.JwtService;
import com.Vicvin.Bookflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public String logout(String token) {
        return "User logged out successfully!";
    }
}
