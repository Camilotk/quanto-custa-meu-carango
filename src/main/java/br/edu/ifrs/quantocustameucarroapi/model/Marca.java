package br.edu.ifrs.quantocustameucarroapi.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;

@Embeddable
public class Marca {
	
	@Max(50)
	private String fabricante;

	@Max(50)
	private String modelo;

	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
