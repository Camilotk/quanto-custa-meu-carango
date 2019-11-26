package br.edu.ifrs.quantocustameucarroapi.event.listener;

import java.math.BigInteger;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.quantocustameucarroapi.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recurso_criado) {
		HttpServletResponse response = recurso_criado.getResponse();
		BigInteger id = recurso_criado.getId();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				   .path("/{id}")
				   .buildAndExpand(id)
				   .toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}
	
}