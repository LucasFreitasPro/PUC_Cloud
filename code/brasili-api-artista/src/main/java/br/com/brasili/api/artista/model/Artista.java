package br.com.brasili.api.artista.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idArtista;

	@Column(nullable = false, unique = true, length = 100)
	private String nome;

	@Column(length = 200)
	private String descricao;

	@Column(nullable = false)
	private LocalDate nascimento;

	private LocalDate falecimento;

	@Column(length = 100)
	private String paisOrigem;

	@ManyToOne
	@JoinColumn(name = "id_epoca", referencedColumnName = "id_epoca")
	private Epoca epoca;

	@ManyToOne
	@JoinColumn(name = "id_estilo", referencedColumnName = "id_estilo")
	private Estilo estilo;

	public UUID getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(UUID idArtista) {
		this.idArtista = idArtista;
	}

	public Epoca getEpoca() {
		return epoca;
	}

	public void setEpoca(Epoca epoca) {
		this.epoca = epoca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public LocalDate getFalecimento() {
		return falecimento;
	}

	public void setFalecimento(LocalDate falecimento) {
		this.falecimento = falecimento;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
