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
public class Epoca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_epoca")
	private UUID idEpoca;

	@Column(nullable = false, unique = true, length = 100)
	private String descricao;

	public UUID getIdEpoca() {
		return idEpoca;
	}

	public void setIdEpoca(UUID idEpoca) {
		this.idEpoca = idEpoca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
