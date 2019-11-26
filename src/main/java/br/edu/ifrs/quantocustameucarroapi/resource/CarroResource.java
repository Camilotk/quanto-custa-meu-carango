package br.edu.ifrs.quantocustameucarroapi.resource;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import br.edu.ifrs.quantocustameucarroapi.model.Carro;
import br.edu.ifrs.quantocustameucarroapi.repository.CarroRepository;
import br.edu.ifrs.quantocustameucarroapi.service.CarroService;

@RestController 
@RequestMapping("/carros")
public class CarroResource {
	@Autowired
	private CarroRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public List<Carro> index() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Carro> store(@RequestBody Carro carro, HttpServletResponse response){
		Carro carro_salvo = repository.save(carro);
	    publisher.publishEvent(new RecursoCriadoEvent(this, response, carro_salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(carro_salvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> show(@PathVariable BigInteger id) {
		Carro carro = repository.findById(id).get();
		return (carro != null) ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable BigInteger id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro> update(@PathVariable BigInteger id, @Valid @RequestBody Carro carro) {
		Carro carro_salvo = carroService.atualizar(id, carro);
		return ResponseEntity.ok(carro_salvo);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable BigInteger id, @RequestBody Boolean ativo) {
		carroService.atualizarPropriedadeAtivo(id, ativo);
	}

}