package br.com.brasili.api.objetoarte.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ObjetoArteRegisteringRequest {

	@NotNull
	@Valid
	private EpocaRequest epoca;

	private String paisOrigem;

	@NotNull
	private LocalDate anoCriacao;

	@NotBlank
	private String titulo;

	private String descricao;

	@NotNull
	private LocalDate dataAquisicao;

	@NotNull
	private Double custoAquisicao;

	@NotNull
	private Boolean exposto;

	@Valid
	@Size(min = 1)
	private List<UUID> artistas;

	private UUID idColecao;

	@NotNull
	@Valid
	private ObraArteRegisteringRequest obraArte;

	public EpocaRequest getEpoca() {
		return epoca;
	}

	public void setEpoca(EpocaRequest epoca) {
		this.epoca = epoca;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public LocalDate getAnoCriacao() {
		return anoCriacao;
	}

	public void setAnoCriacao(LocalDate anoCriacao) {
		this.anoCriacao = anoCriacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Double getCustoAquisicao() {
		return custoAquisicao;
	}

	public void setCustoAquisicao(Double custoAquisicao) {
		this.custoAquisicao = custoAquisicao;
	}

	public Boolean getExposto() {
		return exposto;
	}

	public void setExposto(Boolean exposto) {
		this.exposto = exposto;
	}

	public List<UUID> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<UUID> artistas) {
		this.artistas = artistas;
	}

	public ObraArteRegisteringRequest getObraArte() {
		return obraArte;
	}

	public void setObraArte(ObraArteRegisteringRequest obraArte) {
		this.obraArte = obraArte;
	}

	public UUID getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(UUID idColecao) {
		this.idColecao = idColecao;
	}
}
