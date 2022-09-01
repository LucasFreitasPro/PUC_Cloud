package br.com.brasili.api.objetoarte.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import br.com.brasili.api.objetoarte.dto.ObjetoArteRegisteringRequest;
import br.com.brasili.api.objetoarte.dto.ObjetoArteUpdatingRequest;
import br.com.brasili.api.objetoarte.model.ObjetoArte;
import br.com.brasili.api.objetoarte.service.ObjetoArteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/objetos-arte")
public class ObjetoArteRestController {

	private final ObjetoArteService service;

	public ObjetoArteRestController(ObjetoArteService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os objetos de arte")
	@ApiResponse(responseCode = "200", description = "Lista de objetos de arte cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum objeto de arte encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<ObjetoArte>> getAll() {
		List<ObjetoArte> all = this.service.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@Operation(summary = "Recupera um objeto de arte pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Objeto de arte cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Objeto de arte não cadastrado", content = @Content)
	@GetMapping("/{idObjetoArte}")
	public ResponseEntity<ObjetoArte> getOne(@PathVariable(name = "idObjetoArte", required = true) UUID idObjetoArte) {
		Optional<ObjetoArte> optional = this.service.findById(idObjetoArte);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra um novo objeto de arte")
	@ApiResponse(responseCode = "201", description = "Objeto de arte cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "409", description = "O objeto de arte já se encontra cadastrado na coleção informada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ObjetoArteRegisteringRequest objetoArteRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

//		if (this.service.findByTitulo(idProprietario, idColecao, objetoArteRequest.getTitulo()).isPresent()) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("O objeto " + objetoArteRequest.getTitulo() + " já está cadastrado");
//		}

		ObjetoArte objetoArte = this.service.create(objetoArteRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(objetoArte);
	}

	@Operation(summary = "Atualiza um objeto de arte pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Objeto de arte atualizado com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "O título informado já se encontra cadastrado em outro Objeto de arte na coleção informada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PutMapping("/{idObjetoArte}")
	public ResponseEntity<Object> update(@PathVariable(name = "idObjetoArte", required = true) UUID idObjetoArte, @RequestBody ObjetoArteUpdatingRequest objetoArteUpdatingRequest) {
		ObjetoArte objetoArte = this.service.update(objetoArteUpdatingRequest);
		return ResponseEntity.ok(objetoArte);
	}

	@Operation(summary = "Exclui um objeto de arte pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Objeto de arte excluído com sucesso", content = @Content)
	@DeleteMapping("/{idObjetoArte}")
	public ResponseEntity<Object> delete(@PathVariable(name = "idObjetoArte", required = true) UUID idObjetoArte) {
		this.service.deleteById(idObjetoArte);
		return ResponseEntity.noContent().build();
	}
}
