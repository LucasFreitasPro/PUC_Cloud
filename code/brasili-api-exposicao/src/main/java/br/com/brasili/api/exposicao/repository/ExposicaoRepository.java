package br.com.brasili.api.exposicao.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.exposicao.model.Exposicao;

public interface ExposicaoRepository extends JpaRepository<Exposicao, UUID> {

	Optional<Exposicao> findByNomeAndDataInicio(String nome, LocalDateTime dataInicio);
}
