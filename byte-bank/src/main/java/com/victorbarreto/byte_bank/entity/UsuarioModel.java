package com.victorbarreto.byte_bank.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;


    //Procure o mapeamento na classe ContaModel, no campo chamado usuarioModel
    @OneToOne(mappedBy = "usuarioModel", cascade = CascadeType.ALL)
    private ContaModel contaModel;

    public UsuarioModel() {
    }

    public UsuarioModel(ContaModel contaModel, String cpf, String email, String nome, String senha) {
        this.contaModel = contaModel;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public ContaModel getContaModel() {
        return contaModel;
    }

    public void setContaModel(ContaModel contaModel) {
        this.contaModel = contaModel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Este método retorna as permissões/cargos do usuário (ex: "ROLE_ADMIN", "ROLE_USER").
        // Por enquanto, vamos deixar simples e não usar cargos. Retornamos uma lista vazia.
        return List.of();
    }

    @Override
    public String getPassword() {
        // O Spring Security chamará este método para pegar a senha (já criptografada) do usuário.
        return this.senha;
    }

    @Override
    public String getUsername() {
        // O Spring Security chamará este método para pegar o "username".
        // No nosso sistema, usaremos o e-mail como username.
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
