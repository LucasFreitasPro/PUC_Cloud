package br.com.brasili.api.emprestimo.model;

import java.time.LocalDate;
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

@Entity
@Table
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_emprestimo")
	private UUID idEmprestimo;

	private UUID idMuseuAutorEmprestimo;

	@OneToMany
	@JoinColumn(name = "id_emprestimo", referencedColumnName = "id_emprestimo")
	private List<ObjetoArte> objetosArte;

	private LocalDate dataEmprestimo;

	private LocalDate dataDevolucao;

	public UUID getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(UUID idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public UUID getIdMuseuAutorEmprestimo() {
		return idMuseuAutorEmprestimo;
	}

	public void setIdMuseuAutorEmprestimo(UUID idMuseuAutorEmprestimo) {
		this.idMuseuAutorEmprestimo = idMuseuAutorEmprestimo;
	}

	public List<ObjetoArte> getObjetosArte() {
		return objetosArte;
	}

	public void setObjetosArte(List<ObjetoArte> objetosArte) {
		this.objetosArte = objetosArte;
	}
}
