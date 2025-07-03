package com.victorbarreto.byte_bank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaqueDTO(@NotNull(message = "O número da conta é obrigatório") Integer numConta,

                       @NotNull(message = "O número da agência é obrigatório") Integer agencia,

                       @NotNull(message = "O valor é obrigatório") @Positive(message = "O valor do depósito deve ser positivo") BigDecimal valor) {
}
