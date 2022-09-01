package br.com.brasili.api.emprestimo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.emprestimo.model.ObjetoArte;
import br.com.brasili.api.emprestimo.service.ObjetoArteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/emprestimos/{idEmprestimo}/objetos-arte")
public class ObjetoArteRestController {

	private final ObjetoArteService service;

	public ObjetoArteRestController(ObjetoArteService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os objetos de arte pelo identificador do empréstimo")
	@ApiResponse(responseCode = "200", description = "Lista de objetos de arte cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum objeto de arte encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<ObjetoArte>> getAll(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo) {
		List<ObjetoArte> all = this.service.findAll(idEmprestimo);
		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@Operation(summary = "Recupera um objeto de arte pelo identificador do empréstimo")
	@ApiResponse(responseCode = "200", description = "Objeto de arte encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Objeto de arte não encontrado", content = @Content)
	@GetMapping("/{idObjetoArte}")
	public ResponseEntity<ObjetoArte> getOne(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo, @PathVariable(name = "idObjetoArte", required = true) UUID idObjetoArte) {
		Optional<ObjetoArte> optional = this.service.findById(idEmprestimo, idObjetoArte);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Exclui um objeto de arte pelo seu identificador e pelo identificador do empréstimo")
	@ApiResponse(responseCode = "204", description = "Empréstimo excluído com sucesso", content = @Content)
	@DeleteMapping("/{idObjetoArte}")
	public ResponseEntity<Object> delete(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo, @PathVariable(name = "idObjetoArte", required = true) UUID idObjetoArte) {
		this.service.deleteById(idEmprestimo, idObjetoArte);
		return ResponseEntity.noContent().build();
	}
}
