package com.example.demo.security;

import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        String header = http.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            log.info("JWT filter invoked, token={}", token);
            if (jwtService.isValid(token)) {
                UUID userId = jwtService.extractUserId(token);
                UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//                AuthUser authUser = new AuthUser(user.getId(), user.getEmail());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
//                                authUser,
                                user,
                                null,
                                Collections.emptyList()
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
