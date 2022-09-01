package br.com.brasili.api.exposicao.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_exposicao_nome_data_inicio", columnNames = { "nome", "dataInicio" }) })
public class Exposicao {

	@Id
	@Column(name = "id_exposicao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idExposicao;

	@Column(nullable = false, length = 100)
	private String nome;

	private String descricao;

	@Column(nullable = false)
	private LocalDateTime dataInicio;

	@Column(nullable = false)
	private LocalDateTime dataEncerramento;

	@OneToMany
	@JoinColumn(name = "id_exposicao", referencedColumnName = "id_exposicao")
	private List<ObjetoArte> objetosArte;

	public Exposicao() {

	}

	public Exposicao(UUID idExposicao) {
		this.idExposicao = idExposicao;
	}

	public UUID getIdExposicao() {
		return idExposicao;
	}

	public void setIdExposicao(UUID idExposicao) {
		this.idExposicao = idExposicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public List<ObjetoArte> getObjetosArte() {
		return objetosArte;
	}

	public void setObjetosArte(List<ObjetoArte> objetosArte) {
		this.objetosArte = objetosArte;
	}
}
