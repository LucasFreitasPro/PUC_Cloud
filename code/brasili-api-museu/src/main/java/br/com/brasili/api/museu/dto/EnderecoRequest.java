package br.com.brasili.api.museu.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {

	@NotBlank
	@Max(value = 10)
	private String tipoLogradouro;

	@NotBlank
	@Max(value = 150)
	private String logradouro;

	@NotNull
	private Integer numero;

	@Max(value = 30)
	private String complemento;

	@NotBlank
	@Max(value = 100)
	private String bairro;

	@NotBlank
	@Max(value = 150)
	private String cidade;

	@NotBlank
	@Max(value = 50)
	private String estado;

	@NotBlank
	@Max(value = 9)
	private String cep;

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
