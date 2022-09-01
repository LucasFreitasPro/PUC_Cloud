package br.com.brasili.api.artista.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.artista.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, UUID> {

	Optional<Artista> findByNome(String nome);
}
