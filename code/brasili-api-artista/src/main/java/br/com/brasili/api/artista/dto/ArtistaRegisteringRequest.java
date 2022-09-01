package br.com.brasili.api.artista.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class ArtistaRegisteringRequest {

	@NotBlank
	private String epocaDescricao;

	@NotBlank(message = "Campo obrigatório")
	private String nome;

	private LocalDate nascimento;

	private LocalDate falecimento;

	private String paisOrigem;

	private String estiloDominante;

	private String descricao;

	public String getEpocaDescricao() {
		return epocaDescricao;
	}

	public void setEpocaDescricao(String epocaDescricao) {
		this.epocaDescricao = epocaDescricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public LocalDate getFalecimento() {
		return falecimento;
	}

	public void setFalecimento(LocalDate falecimento) {
		this.falecimento = falecimento;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getEstiloDominante() {
		return estiloDominante;
	}

	public void setEstiloDominante(String estiloDominante) {
		this.estiloDominante = estiloDominante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
