package com.victorbarreto.byte_bank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.victorbarreto.byte_bank.dto.ContaDTO;
import com.victorbarreto.byte_bank.dto.DepositoDTO;
import com.victorbarreto.byte_bank.entity.ContaModel;
import com.victorbarreto.byte_bank.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaDTO deposito(DepositoDTO depositoDTO) {
        ContaModel conta = contaRepository.findByNumContaAndAgencia(depositoDTO.numConta(), depositoDTO.agencia())
                .orElseThrow(() -> new RuntimeException("Conta ou agência inválida"));

        conta.deposito(depositoDTO.valor());

        ContaModel contaSalva = contaRepository.save(conta);

        return new ContaDTO(contaSalva.getAgencia(), contaSalva.getNumConta(), contaSalva.getSaldo());
    }
}
