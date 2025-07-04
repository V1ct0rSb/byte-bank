package com.victorbarreto.byte_bank;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.victorbarreto.byte_bank.entity.ContaModel;
import com.victorbarreto.byte_bank.entity.UsuarioModel;
import com.victorbarreto.byte_bank.repository.UsuarioRespository;

@SpringBootApplication
public class ByteBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByteBankApplication.class, args);
	}

	// NOVO BEAN PARA CRIAR UM USUÁRIO DE TESTE:
	@Bean
	public CommandLineRunner commandLineRunner(UsuarioRespository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Verifica se o usuário de teste já existe no banco para não criar duplicatas
			if (repository.findByEmail("admin@bytebank.com").isEmpty()) {

				System.out.println("--- Criando usuário de teste: admin@bytebank.com ---");

				// Cria o usuário
				UsuarioModel admin = new UsuarioModel();
				admin.setNome("Admin");
				admin.setCpf("00000000000");
				admin.setEmail("admin@bytebank.com");
				// Criptografa a senha usando o PasswordEncoder
				admin.setSenha(passwordEncoder.encode("123456"));

				// Cria a conta associada
				ContaModel contaAdmin = new ContaModel();
				contaAdmin.setAgencia(1);
				contaAdmin.setNumConta(12345);
				contaAdmin.setSaldo(BigDecimal.valueOf(1000));

				// Associa os dois
				admin.setContaModel(contaAdmin);
				contaAdmin.setUsuarioModel(admin);

				// Salva o usuário (a conta será salva em cascata)
				repository.save(admin);

				System.out.println("--- Usuário de teste criado com sucesso! ---");
			}
		};
	}

}
