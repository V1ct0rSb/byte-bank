package com.victorbarreto.byte_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.byte_bank.entity.UsuarioModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public interface UsuarioRespository extends JpaRepository<UsuarioModel, Long> {
    boolean existsByCpf(@NotBlank(message = "CPF é obrigatório") @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos") String cpf);

    boolean existsByEmail(@Email(message = "Email inválido") String email);
}
