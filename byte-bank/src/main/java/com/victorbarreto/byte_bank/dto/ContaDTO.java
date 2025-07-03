package com.victorbarreto.byte_bank.dto;

import java.math.BigDecimal;

public record ContaDTO(Integer agencia, Integer numConta, BigDecimal saldo, String nomeTitular) {

}
