package br.com.brasili.api.objetoarte.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ObraArte {

	@Id
	@Column(name = "id_obra_arte")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idObraArte;

	private Float peso;

	private Integer altura;

	private Integer largura;

	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_estilo_obra_arte", referencedColumnName = "id_estilo_obra_arte")
	private EstiloObraArte estiloObraArte;

	@ManyToOne
	@JoinColumn(name = "id_tipo_obra_arte", referencedColumnName = "id_tipo_obra_arte")
	private TipoObraArte tipoObraArte;

	@ManyToMany
	@JoinTable(name = "obra_arte_material", joinColumns = @JoinColumn(name = "id_obra_arte"), inverseJoinColumns = @JoinColumn(name = "id_material"))
	private List<Material> materiais;

	public UUID getIdObraArte() {
		return idObraArte;
	}

	public void setIdObraArte(UUID idObraArte) {
		this.idObraArte = idObraArte;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public EstiloObraArte getEstiloObraArte() {
		return estiloObraArte;
	}

	public void setEstiloObraArte(EstiloObraArte estiloObraArte) {
		this.estiloObraArte = estiloObraArte;
	}

	public TipoObraArte getTipoObraArte() {
		return tipoObraArte;
	}

	public void setTipoObraArte(TipoObraArte tipoObraArte) {
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
}
