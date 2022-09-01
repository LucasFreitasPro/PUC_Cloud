package br.com.brasili.api.objetoarte.dto;

import javax.validation.constraints.NotBlank;

public class EpocaRequest {

	@NotBlank(message = "Campo obrigatório")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
