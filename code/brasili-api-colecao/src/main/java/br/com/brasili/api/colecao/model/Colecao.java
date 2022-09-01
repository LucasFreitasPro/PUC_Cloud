package br.com.brasili.api.colecao.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Colecao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idColecao;

	private UUID idMuseu;

	@Column(nullable = false, length = 100)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_tipo_colecao", referencedColumnName = "id_tipo_colecao")
	private TipoColecao tipoColecao;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_contato", referencedColumnName = "id_contato")
	private Contato contato;

	public UUID getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(UUID idColecao) {
		this.idColecao = idColecao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UUID getIdMuseu() {
		return idMuseu;
	}

	public void setIdMuseu(UUID idMuseu) {
		this.idMuseu = idMuseu;
	}

	public TipoColecao getTipoColecao() {
		return tipoColecao;
	}

	public void setTipoColecao(TipoColecao tipoColecao) {
		this.tipoColecao = tipoColecao;
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
}
