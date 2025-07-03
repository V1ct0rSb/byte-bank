package com.victorbarreto.byte_bank.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class ContaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 5)
    private Integer agencia;
    @Column(nullable = false, unique = true)
    private Integer numConta;
    @Column(nullable = false)
    private BigDecimal saldo;

    @JoinColumn(name = "usuario_id")
    @OneToOne
    private UsuarioModel usuarioModel;

    public ContaModel() {
    }

    public ContaModel(Integer agencia, Integer numConta, UsuarioModel usuarioModel, BigDecimal saldo) {
        this.agencia = agencia;
        this.numConta = numConta;
        this.usuarioModel = usuarioModel;
        this.saldo = saldo;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumConta() {
        return numConta;
    }

    public void setNumConta(Integer numConta) {
        this.numConta = numConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }


    public BigDecimal deposito(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
        return this.saldo;
    }

}
