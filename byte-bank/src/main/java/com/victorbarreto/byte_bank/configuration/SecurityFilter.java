package com.victorbarreto.byte_bank.configuration; // ou o pacote que você preferir

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.victorbarreto.byte_bank.repository.UsuarioRespository;
import com.victorbarreto.byte_bank.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Anotação para que o Spring possa injetá-lo em outras classes
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRespository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. Recupera o token do cabeçalho da requisição
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // 2. Valida o token e pega o subject (e-mail do usuário)
            var subject = tokenService.getSubject(tokenJWT);
            // 3. Busca o usuário no banco de dados pelo e-mail
            var usuario = usuarioRepository.findByEmail(subject)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

            // 4. Cria o objeto de autenticação para o Spring
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            // 5. "Força" a autenticação do usuário para esta requisição
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 6. Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // O token vem após "Bearer "
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}