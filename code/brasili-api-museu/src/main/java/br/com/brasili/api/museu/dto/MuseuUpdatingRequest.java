package br.com.brasili.api.museu.dto;

public class MuseuUpdatingRequest {

	private String nome;

	private String sigla;

	private ContatoRequest contato;

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
