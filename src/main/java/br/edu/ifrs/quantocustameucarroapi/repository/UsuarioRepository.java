package br.edu.ifrs.quantocustameucarroapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.quantocustameucarroapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	Usuario findByLogin(String login);
}
