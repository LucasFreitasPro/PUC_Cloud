package br.com.brasili.api.colecao.dto;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ColecaoRegisteringRequest {

	@NotBlank
	private String nome;

	@NotNull
	private UUID idMuseu;

	@NotBlank
	private String tipoColecao;

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

	public UUID getIdMuseu() {
		return idMuseu;
	}

	public void setIdMuseu(UUID idMuseu) {
		this.idMuseu = idMuseu;
	}

	public String getTipoColecao() {
		return tipoColecao;
	}

	public void setTipoColecao(String tipoColecao) {
		this.tipoColecao = tipoColecao;
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
