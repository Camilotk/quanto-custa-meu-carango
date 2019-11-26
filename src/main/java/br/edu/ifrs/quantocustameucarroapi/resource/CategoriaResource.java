package br.edu.ifrs.quantocustameucarroapi.resource;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.quantocustameucarroapi.event.RecursoCriadoEvent;
import br.edu.ifrs.quantocustameucarroapi.model.Categoria;
import br.edu.ifrs.quantocustameucarroapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Categoria> index() { 
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> store(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoria_salva = repository.save(categoria);
	    publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria_salva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria_salva);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> show(@PathVariable BigInteger id) {
		Optional<Categoria> resultado = repository.findById(id);
		Categoria categoria = (resultado.isPresent()) ? resultado.get() : null; 
		return (categoria != null) ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable BigInteger id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable BigInteger id, @RequestBody Categoria categoria) {
		Categoria categoria_salva = repository.findById(id).get();
		
		if (categoria_salva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(categoria, categoria_salva, "id");
		repository.save(categoria_salva);
		
		return ResponseEntity.ok(categoria_salva);
	}
}
