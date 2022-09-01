package br.com.brasili.api.objetoarte.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.objetoarte.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

}
