package com.rodrigo.minhasfinancas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigo.minhasfinancas.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
