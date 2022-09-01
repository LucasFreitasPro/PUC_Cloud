package br.com.brasili.api.objetoarte.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ObjetoArte {

	@Id
	@Column(name = "id_objeto_arte")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idObjetoArte;

	private UUID idColecao;

	@Column(nullable = false)
	private String identificador;

	private String paisOrigem;

	private LocalDate anoCriacao;

	@Column(nullable = false, unique = true)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	private LocalDate dataAquisicao;

	private Double custoAquisicao;

	@Column(nullable = false)
	private boolean exposto;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "id_obra_arte", referencedColumnName = "id_obra_arte")
	private ObraArte obraArte;

//	@ManyToOne
//	@JoinColumn(name = "id_emprestimo", referencedColumnName = "id_emprestimo")
//	private EmprestimoRegisteringRequest emprestimo;

	@ManyToOne
	@JoinColumn(name = "id_epoca", referencedColumnName = "id_epoca")
	private Epoca epoca;

	@OneToMany
	@JoinColumn(name = "id_objeto_arte", referencedColumnName = "id_objeto_arte")
	private List<Artista> artistas;

	public UUID getIdObjetoArte() {
		return idObjetoArte;
	}

	public void setIdObjetoArte(UUID idObjetoArte) {
		this.idObjetoArte = idObjetoArte;
	}

	public ObraArte getObraArte() {
		return obraArte;
	}

	public void setObraArte(ObraArte obraArte) {
		this.obraArte = obraArte;
	}

	public Epoca getEpoca() {
		return epoca;
	}

	public void setEpoca(Epoca epoca) {
		this.epoca = epoca;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public LocalDate getAnoCriacao() {
		return anoCriacao;
	}

	public void setAnoCriacao(LocalDate anoCriacao) {
		this.anoCriacao = anoCriacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Double getCustoAquisicao() {
		return custoAquisicao;
	}

	public void setCustoAquisicao(Double custoAquisicao) {
		this.custoAquisicao = custoAquisicao;
	}

	public boolean isExposto() {
		return exposto;
	}

	public void setExposto(boolean exposto) {
		this.exposto = exposto;
	}

	public UUID getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(UUID idColecao) {
		this.idColecao = idColecao;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
}
