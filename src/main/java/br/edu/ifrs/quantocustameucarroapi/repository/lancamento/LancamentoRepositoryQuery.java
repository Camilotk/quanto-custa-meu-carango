package br.edu.ifrs.quantocustameucarroapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifrs.quantocustameucarroapi.model.Lancamento;
import br.edu.ifrs.quantocustameucarroapi.repository.filter.LancamentoFilter;
import br.edu.ifrs.quantocustameucarroapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
