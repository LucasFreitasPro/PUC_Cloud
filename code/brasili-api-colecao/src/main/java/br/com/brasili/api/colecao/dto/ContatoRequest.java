package br.com.brasili.api.colecao.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class ContatoRequest {

	@NotBlank
	@Max(value = 50)
	private String nome;

	@Max(value = 50)
	private String descricao;

	@NotBlank
	@Max(value = 15)
	private String telefone;

	@Max(value = 100)
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
