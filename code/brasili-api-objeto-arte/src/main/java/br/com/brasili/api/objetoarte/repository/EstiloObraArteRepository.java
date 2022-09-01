package br.com.brasili.api.objetoarte.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.objetoarte.model.EstiloObraArte;

public interface EstiloObraArteRepository extends JpaRepository<EstiloObraArte, UUID> {

}
