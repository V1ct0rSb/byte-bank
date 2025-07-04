package com.victorbarreto.byte_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.victorbarreto.byte_bank.repository.UsuarioRespository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRespository usuarioRepository; // Injete seu repositório de usuário

    // Este método é chamado pelo Spring Security quando um usuário tenta se autenticar
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // A nossa regra de negócio é que o 'username' é o e-mail do usuário.
        // Então, buscamos o usuário por e-mail no banco de dados.
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Dados de usuário inválidos!"));
    }
}
