package com.victorbarreto.byte_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.byte_bank.dto.UsuarioCreateDTO;
import com.victorbarreto.byte_bank.dto.UsuarioResponseDTO;
import com.victorbarreto.byte_bank.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO usuario = usuarioService.criarUsuario(usuarioCreateDTO);
        return ResponseEntity.status(201).body(usuario);
    }
}
