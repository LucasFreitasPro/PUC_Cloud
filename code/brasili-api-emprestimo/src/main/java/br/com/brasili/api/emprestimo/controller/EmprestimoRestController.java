package br.com.brasili.api.emprestimo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import br.com.brasili.api.emprestimo.dto.EmprestimoRegisteringRequest;
import br.com.brasili.api.emprestimo.dto.EmprestimoUpdatingRequest;
import br.com.brasili.api.emprestimo.model.Emprestimo;
import br.com.brasili.api.emprestimo.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoRestController {

	private final EmprestimoService service;

	public EmprestimoRestController(EmprestimoService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os empréstimos")
	@ApiResponse(responseCode = "200", description = "Lista de empréstimos cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Emprestimo.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum empréstimo encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<Emprestimo>> getAll() {
		List<Emprestimo> all = this.service.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@Operation(summary = "Recupera um empréstimo pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Empréstimo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Emprestimo.class)))
	@ApiResponse(responseCode = "404", description = "Empréstimo não encontrado", content = @Content)
	@GetMapping("/{idEmprestimo}")
	public ResponseEntity<Emprestimo> getOne(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo) {
		Optional<Emprestimo> optional = this.service.findById(idEmprestimo);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra um novo empréstimo")
	@ApiResponse(responseCode = "201", description = "Empréstimo cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Emprestimo.class)))
	@ApiResponse(responseCode = "409", description = "O objeto de arte já se encontra emprestado dentro do período informado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody EmprestimoRegisteringRequest emprestimoRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

		Emprestimo emprestimo = this.service.create(emprestimoRegisteringRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
	}

	@Operation(summary = "Atualiza a data de devolução de um empréstimo pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Empréstimo atualizado com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Emprestimo.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "A data de devolução não pode ser menor que a data do empréstimo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PatchMapping("/{idEmprestimo}")
	public ResponseEntity<Object> update(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo, @RequestBody EmprestimoUpdatingRequest emprestimoUpdatingRequest) {
		Emprestimo emprestimo = this.service.update(emprestimoUpdatingRequest);
		return ResponseEntity.ok(emprestimo);
	}

	@Operation(summary = "Exclui um empréstimo pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Empréstimo excluído com sucesso", content = @Content)
	@DeleteMapping("/{idEmprestimo}")
	public ResponseEntity<Object> delete(@PathVariable(name = "idEmprestimo", required = true) UUID idEmprestimo) {
		this.service.deleteById(idEmprestimo);
		return ResponseEntity.noContent().build();
	}
}
