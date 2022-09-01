package br.com.brasili.api.emprestimo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.emprestimo.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {

}
