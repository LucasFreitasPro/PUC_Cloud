package br.com.brasili.api.exposicao.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brasili.api.exposicao.model.ObjetoArte;

public interface ObjetoArteRepository extends JpaRepository<ObjetoArte, UUID> {

	@Query(value = "SELECT o.* FROM exposicao_objeto_arte o WHERE o.id_exposicao = :idExposicao", nativeQuery = true)
	List<ObjetoArte> findByIdExposicao(@Param("idExposicao") UUID idExposicao);
}
