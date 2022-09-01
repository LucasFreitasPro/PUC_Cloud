package br.com.brasili.api.objetoarte.dto;

import javax.validation.constraints.NotBlank;

public class TipoObraArteRequest {

	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
