package br.com.brasili.api.objetoarte.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TipoObraArte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_obra_arte")
	private UUID idTipoObraArte;

	@Column(nullable = false, unique = true, length = 100)
	private String descricao;

	public UUID getIdTipoObraArte() {
		return idTipoObraArte;
	}

	public void setIdTipoObraArte(UUID idTipoObraArte) {
		this.idTipoObraArte = idTipoObraArte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
