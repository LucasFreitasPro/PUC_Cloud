package br.com.brasili.api.museu.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brasili.api.museu.model.Museu;

public interface MuseuRepository extends JpaRepository<Museu, UUID> {

	@Query(value = "SELECT u.* FROM museu u JOIN endereco e ON u.id_endereco = e.id_endereco WHERE u.nome = :nome AND e.cidade = :cidade", nativeQuery = true)
	Optional<Museu> findByNomeAndCidade(@Param("nome") String nome, @Param("cidade") String cidade);
}
