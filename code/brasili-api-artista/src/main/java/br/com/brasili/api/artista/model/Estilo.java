package br.com.brasili.api.artista.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Estilo {

	@Id
	@Column(name = "id_estilo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idEstilo;

	@Column(nullable = false, unique = true, length = 100)
	private String nome;

	public UUID getIdEstilo() {
		return idEstilo;
	}

	public void setIdEstilo(UUID idEstilo) {
		this.idEstilo = idEstilo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
