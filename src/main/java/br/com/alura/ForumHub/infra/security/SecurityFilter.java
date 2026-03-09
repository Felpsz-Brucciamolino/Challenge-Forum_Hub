package br.com.alura.ForumHub.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var token = recuperarToken(request);

        if(token != null){

            var login = tokenService.validarToken(token);

            if(login != null){

                var authentication = new UsernamePasswordAuthenticationToken(
                        login,
                        null,
                        Collections.emptyList()
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){

        var header = request.getHeader("Authorization");

        if(header == null) return null;

        return header.replace("Bearer ", "");
    }
}