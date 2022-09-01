package br.com.brasili.api.colecao.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.colecao.model.Colecao;

public interface ColecaoRepository extends JpaRepository<Colecao, UUID> {

	Optional<Colecao> findByNomeAndIdMuseu(String nome, UUID idMuseu);

	List<Colecao> findByIdMuseu(UUID idMuseu);
}
