package br.com.brasili.api.museu.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MuseuRegisteringRequest {

	@NotBlank
	private String nome;

	private String sigla;

	@NotNull
	@Valid
	private ContatoRequest contato;

	@NotNull
	@Valid
	private EnderecoRequest endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ContatoRequest getContato() {
		return contato;
	}

	public void setContato(ContatoRequest contato) {
		this.contato = contato;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequest endereco) {
		this.endereco = endereco;
	}
}
