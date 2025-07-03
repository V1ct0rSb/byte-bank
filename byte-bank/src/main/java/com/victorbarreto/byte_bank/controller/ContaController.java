package com.victorbarreto.byte_bank.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.byte_bank.dto.ContaDTO;
import com.victorbarreto.byte_bank.dto.DepositoDTO;
import com.victorbarreto.byte_bank.service.ContaService;

@RestController
@RequestMapping(value = "/api")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PutMapping("/contas")
    public ResponseEntity<ContaDTO> deposito(@RequestBody DepositoDTO depositoDTO) {
        ContaDTO contaDTO = contaService.deposito(
                depositoDTO.valor(),
                depositoDTO.numConta(),
                depositoDTO.agencia()
        );
        return ResponseEntity.ok(contaDTO);
    }
}
