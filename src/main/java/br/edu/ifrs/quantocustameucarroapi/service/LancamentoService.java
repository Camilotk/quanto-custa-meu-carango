package br.edu.ifrs.quantocustameucarroapi.service;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.quantocustameucarroapi.model.Carro;
import br.edu.ifrs.quantocustameucarroapi.model.Lancamento;
import br.edu.ifrs.quantocustameucarroapi.repository.CarroRepository;
import br.edu.ifrs.quantocustameucarroapi.repository.LancamentoRepository;
import br.edu.ifrs.quantocustameucarroapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private CarroRepository carro_repository;

	@Autowired
	private LancamentoRepository lancamento_repository;

	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);

		return lancamento_repository.save(lancamento);
	}

	public Lancamento atualizar(BigInteger id, Lancamento lancamento) {
		Lancamento lancamento_salvo = buscarLancamentoExistente(id);
		if (!lancamento.getCarro().equals(lancamento_salvo.getCarro())) {
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamento_salvo, "codigo");

		return lancamento_repository.save(lancamento_salvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Carro carro = null;
		if (lancamento.getCarro().getId() != null) {
			carro = carro_repository.findById(lancamento.getCarro().getId()).get();
		}

		if (carro == null || !carro.getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Lancamento buscarLancamentoExistente(BigInteger id) {
		Lancamento lancamentoSalvo = lancamento_repository.findById(id).get();
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

}
