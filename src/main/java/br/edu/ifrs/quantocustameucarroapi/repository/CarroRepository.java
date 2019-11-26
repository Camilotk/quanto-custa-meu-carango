package br.edu.ifrs.quantocustameucarroapi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.quantocustameucarroapi.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, BigInteger>{

}
