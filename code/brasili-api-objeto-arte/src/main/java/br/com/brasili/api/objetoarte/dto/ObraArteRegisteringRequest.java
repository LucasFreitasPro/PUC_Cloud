package br.com.brasili.api.objetoarte.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ObraArteRegisteringRequest {

	@Valid
	@NotNull
	private CategoriaRequest categoria;

	@Valid
	@NotNull
	private EstiloObraArteRequest estiloObraArte;

	@Valid
	@NotNull
	private TipoObraArteRequest tipoObraArte;

	private Float peso;

	private Integer altura;

	private Integer largura;

	@Valid
	@Size(min = 1)
	private List<MaterialRequest> materiais;

	public CategoriaRequest getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRequest categoria) {
		this.categoria = categoria;
	}

	public EstiloObraArteRequest getEstiloObraArte() {
		return estiloObraArte;
	}

	public void setEstiloObraArte(EstiloObraArteRequest estiloObraArte) {
		this.estiloObraArte = estiloObraArte;
	}

	public TipoObraArteRequest getTipoObraArte() {
		return tipoObraArte;
	}

	public void setTipoObraArte(TipoObraArteRequest tipoObraArte) {
		this.tipoObraArte = tipoObraArte;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getLargura() {
		return largura;
	}

	public void setLargura(Integer largura) {
		this.largura = largura;
	}

	public List<MaterialRequest> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<MaterialRequest> materiais) {
		this.materiais = materiais;
	}
}
