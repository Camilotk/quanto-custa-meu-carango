package br.edu.ifrs.quantocustameucarroapi.event;

import java.math.BigInteger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private BigInteger id;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, BigInteger id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public BigInteger getId() {
		return id;
	}
	
}