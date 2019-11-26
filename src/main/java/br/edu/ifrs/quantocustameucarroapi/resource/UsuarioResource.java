package br.edu.ifrs.quantocustameucarroapi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.quantocustameucarroapi.event.RecursoCriadoEvent;
import br.edu.ifrs.quantocustameucarroapi.model.Usuario;
import br.edu.ifrs.quantocustameucarroapi.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Usuario> store(@RequestBody Usuario usuario, HttpServletResponse response){
		usuario.setSenha(encoder_crypt().encode(usuario.getSenha()));
		Usuario usuario_salvo = repository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario_salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario_salvo);
	}
	
	@Bean
	public PasswordEncoder  encoder_crypt() {
	    return new BCryptPasswordEncoder();
	}
}
