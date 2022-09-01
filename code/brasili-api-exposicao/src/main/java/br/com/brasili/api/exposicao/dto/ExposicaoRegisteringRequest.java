package br.com.brasili.api.exposicao.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ExposicaoRegisteringRequest {

	@NotBlank(message = "Campo obrigatório")
	private String nome;

	private String descricao;

	@NotNull(message = "Campo obrigatório")
	private LocalDateTime dataInicio;

	@NotNull(message = "Campo obrigatório")
	private LocalDateTime dataEncerramento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
}
