package br.com.brasili.api.objetoarte.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.objetoarte.model.Epoca;

public interface EpocaRepository extends JpaRepository<Epoca, UUID> {

	Optional<Epoca> findByDescricao(String descricao);
}
