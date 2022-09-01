package br.com.brasili.api.objetoarte.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idMaterial;

	@Column(nullable = false)
	private String descricao;

	@ManyToMany(mappedBy = "materiais")
	private List<ObraArte> objetosArte;

	public UUID getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(UUID idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ObraArte> getObjetosArte() {
		return objetosArte;
	}

	public void setObjetosArte(List<ObraArte> objetosArte) {
		this.objetosArte = objetosArte;
	}
}
