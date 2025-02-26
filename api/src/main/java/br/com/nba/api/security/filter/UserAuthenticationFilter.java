package br.com.nba.api.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.nba.api.entities.User;
import br.com.nba.api.entities.UserDetailsImpl;
import br.com.nba.api.repositories.interfaces.UserRepository;
import br.com.nba.api.security.config.SecurityConfiguration;
import br.com.nba.api.services.interfaces.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                String username = service.getSubjectFromToken(token);
                User user = repository.findByUsername(username).get();
                UserDetails details = new UserDetailsImpl(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(details.getUsername(),
                        null, details.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null)
            return null;
        return header.replace("Bearer ", "");
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(uri);
    }
}
