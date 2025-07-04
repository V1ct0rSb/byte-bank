package com.victorbarreto.byte_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorbarreto.byte_bank.dto.DadosAutenticacaoDTO;
import com.victorbarreto.byte_bank.dto.DadosTokenJWT;
import com.victorbarreto.byte_bank.entity.UsuarioModel;
import com.victorbarreto.byte_bank.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // Injete o AuthenticationManager que criamos
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService; // Injete o serviço de token

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        // Pega o objeto do usuário que foi autenticado
        var usuario = (UsuarioModel) authentication.getPrincipal();

        // Gera o token com base no usuário autenticado
        var tokenJWT = tokenService.gerarToken(usuario);

        // Retorna o token no corpo da resposta
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
