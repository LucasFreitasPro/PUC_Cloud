package br.com.brasili.api.colecao.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoColecao {

	@Id
	@Column(name = "id_tipo_colecao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idTipoColecao;

	@Column(nullable = false, length = 100)
	private String tipo;

	public UUID getIdTipoColecao() {
		return idTipoColecao;
	}

	public void setIdTipoColecao(UUID idTipoColecao) {
		this.idTipoColecao = idTipoColecao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
