package com.victorbarreto.byte_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.byte_bank.dto.ContaDTO;
import com.victorbarreto.byte_bank.dto.DepositoDTO;
import com.victorbarreto.byte_bank.dto.SaqueDTO;
import com.victorbarreto.byte_bank.service.ContaService;

@RestController
@RequestMapping(value = "/api")
public class ContaController {

    @Autowired
    private ContaService contaService;


    @PutMapping("/contas/deposito")
    public ResponseEntity<ContaDTO> deposito(@RequestBody DepositoDTO depositoDTO) {
        ContaDTO contaDTO = contaService.deposito(depositoDTO);
        return ResponseEntity.ok(contaDTO);
    }

    @PutMapping("/contas/saque")
    public ResponseEntity<ContaDTO> saque(@RequestBody SaqueDTO saqueDTO) {
        ContaDTO contaDTO = contaService.saque(saqueDTO);
        return ResponseEntity.ok(contaDTO);

    }

    @GetMapping("contas/{id}")
    public ResponseEntity<ContaDTO> exibirContaId(@PathVariable Long id) {
        ContaDTO contaDTO = contaService.exibirContaId(id);
        return ResponseEntity.ok().body(contaDTO);
    }
}
