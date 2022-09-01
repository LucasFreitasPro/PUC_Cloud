package br.com.brasili.api.artista.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.artista.model.Estilo;

public interface EstiloRepository extends JpaRepository<Estilo, UUID> {

	Optional<Estilo> findByNome(String nome);
}
