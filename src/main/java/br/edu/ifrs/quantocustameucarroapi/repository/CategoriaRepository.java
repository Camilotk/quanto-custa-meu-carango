package br.edu.ifrs.quantocustameucarroapi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.quantocustameucarroapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, BigInteger> {

}
