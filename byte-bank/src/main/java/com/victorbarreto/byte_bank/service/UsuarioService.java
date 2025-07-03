package com.victorbarreto.byte_bank.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.victorbarreto.byte_bank.dto.UsuarioCreateDTO;
import com.victorbarreto.byte_bank.dto.UsuarioResponseDTO;
import com.victorbarreto.byte_bank.entity.ContaModel;
import com.victorbarreto.byte_bank.entity.UsuarioModel;
import com.victorbarreto.byte_bank.repository.ContaRepository;
import com.victorbarreto.byte_bank.repository.UsuarioRespository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContaRepository contaRepository;

    //POST
    public UsuarioResponseDTO criarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioRespository.existsByCpf(usuarioCreateDTO.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (usuarioRespository.existsByEmail(usuarioCreateDTO.email())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setNome(usuarioCreateDTO.nome());
        usuarioModel.setEmail(usuarioCreateDTO.email());
        usuarioModel.setCpf(usuarioCreateDTO.cpf());
        String senhaCriptografada = passwordEncoder.encode(usuarioCreateDTO.senha());
        usuarioModel.setSenha(senhaCriptografada);

        ContaModel contaModel = new ContaModel();
        Integer numConta = gerarNumConta();

        contaModel.setAgencia(0001);
        contaModel.setNumConta(numConta);
        contaModel.setSaldo(BigDecimal.ZERO);

        usuarioModel.setContaModel(contaModel);
        contaModel.setUsuarioModel(usuarioModel);

        UsuarioModel salvo = usuarioRespository.save(usuarioModel);

        return new UsuarioResponseDTO(usuarioModel.getId(), salvo.getNome(), salvo.getEmail());
    }

    private Integer gerarNumConta() {
        int numConta;
        int tentativa = 0;

        do {
            numConta = 1000 + new Random().nextInt(9000);
            tentativa++;
            if (tentativa > 1000) {
                throw new RuntimeException("Não foi possivel gerar um numero de conta unico!!");
            }
        } while (contaRepository.existsByNumConta(numConta));
        return numConta;
    }
}
