package br.edu.ifrs.quantocustameucarroapi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.quantocustameucarroapi.model.Lancamento;
import br.edu.ifrs.quantocustameucarroapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, BigInteger>, LancamentoRepositoryQuery  {

}
