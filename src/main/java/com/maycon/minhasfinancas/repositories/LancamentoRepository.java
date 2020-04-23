package com.maycon.minhasfinancas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maycon.minhasfinancas.model.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
