package br.edu.ifrs.quantocustameucarroapi.service;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifrs.quantocustameucarroapi.model.Carro;
import br.edu.ifrs.quantocustameucarroapi.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;

	public Carro atualizar(BigInteger id, Carro carro) {
		Carro carro_salvo = buscarCarroPeloCodigo(id);
		
		BeanUtils.copyProperties(carro, carro_salvo, "id");
		return repository.save(carro_salvo);
	}

	public void atualizarPropriedadeAtivo(BigInteger id, Boolean ativo) {
		Carro carro_salvo = buscarCarroPeloCodigo(id);
		carro_salvo.setAtivo(ativo);
		repository.save(carro_salvo);
	}
	
	public Carro buscarCarroPeloCodigo(BigInteger id) {
		Carro carro_salvo = repository.findById(id).get();
		if (carro_salvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carro_salvo;
	}
	
}
