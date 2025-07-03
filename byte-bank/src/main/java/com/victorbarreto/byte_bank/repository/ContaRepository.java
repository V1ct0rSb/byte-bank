package com.victorbarreto.byte_bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.byte_bank.entity.ContaModel;

public interface ContaRepository extends JpaRepository<ContaModel, Long> {

    boolean existsByNumConta(int numConta);


    Optional<ContaModel> findByNumContaAndAgencia(Integer numConta, Integer agencia);

}
