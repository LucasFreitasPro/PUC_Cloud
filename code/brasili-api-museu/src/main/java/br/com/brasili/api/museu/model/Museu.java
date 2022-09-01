package br.com.brasili.api.museu.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Museu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_museu")
	private UUID idMuseu;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(length = 10)
	private String sigla;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_contato", referencedColumnName = "id_contato")
	private Contato contato;

	public UUID getIdMuseu() {
		return idMuseu;
	}

	public void setIdMuseu(UUID idMuseu) {
		this.idMuseu = idMuseu;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
