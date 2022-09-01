package br.com.brasili.api.objetoarte.dto;

import javax.validation.constraints.NotBlank;

public class EstiloObraArteRequest {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
