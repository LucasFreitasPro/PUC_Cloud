package br.com.brasili.api.objetoarte.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brasili.api.objetoarte.model.ObjetoArte;

public interface ObjetoArteRepository extends JpaRepository<ObjetoArte, UUID> {

	public static final String SELECT = "SELECT oa.*"
										+ " FROM objeto_arte oa"
										+ " JOIN colecao c ON oa.id_colecao = c.id_colecao"
										+ " JOIN proprietario p ON c.id_proprietario = p.id_proprietario"
										+ " WHERE p.id_proprietario = :idProprietario"
										+ " AND c.id_colecao = :idColecao";

	@Query(value = SELECT, nativeQuery = true)
	List<ObjetoArte> findAll(@Param("idProprietario") UUID idProprietario, @Param("idColecao") UUID idColecao);

	@Query(value = SELECT + " AND oa.titulo = :titulo", nativeQuery = true)
	Optional<ObjetoArte> findByTitulo(@Param("idProprietario") UUID idProprietario, @Param("idColecao") UUID idColecao, @Param("titulo") String titulo);
}
