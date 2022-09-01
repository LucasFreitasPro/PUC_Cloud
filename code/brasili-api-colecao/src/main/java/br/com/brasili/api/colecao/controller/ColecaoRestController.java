package br.com.brasili.api.colecao.controller;

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

import br.com.brasili.api.colecao.dto.ColecaoRegisteringRequest;
import br.com.brasili.api.colecao.dto.ColecaoUpdatingRequest;
import br.com.brasili.api.colecao.enums.TipoColecaoEnum;
import br.com.brasili.api.colecao.model.Colecao;
import br.com.brasili.api.colecao.service.ColecaoService;
import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/colecoes")
public class ColecaoRestController {

	private final ColecaoService service;

	public ColecaoRestController(ColecaoService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todas as coleções cadastradas")
	@ApiResponse(responseCode = "200", description = "Lista de coleções cadastradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Colecao.class)))
	@ApiResponse(responseCode = "404", description = "Nenhuma coleção encontrada", content = @Content)
	@GetMapping
	public ResponseEntity<List<Colecao>> getAll() {
		List<Colecao> all = this.service.findAll();
		if (all != null && !all.isEmpty()) {
			return ResponseEntity.ok(all);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Recupera uma coleção pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Coleção encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Colecao.class)))
	@ApiResponse(responseCode = "404", description = "Coleção não encontrada", content = @Content)
	@GetMapping("/{idColecao}")
	public ResponseEntity<Colecao> getOne(@PathVariable(name = "idColecao", required = true) UUID idColecao) {
		Optional<Colecao> optional = this.service.findById(idColecao);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Recupera todas as coleções cadastradas pelo identificador de um museu")
	@ApiResponse(responseCode = "200", description = "Lista de coleções cadastradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Colecao.class)))
	@ApiResponse(responseCode = "404", description = "Nenhuma coleção encontrada", content = @Content)
	@GetMapping("/museus/{idMuseu}")
	public ResponseEntity<List<Colecao>> getAllByIdMuseu(@PathVariable(name = "idMuseu", required = true) UUID idMuseu) {
		List<Colecao> all = this.service.findByIdMuseu(idMuseu);
		if (all != null && !all.isEmpty()) {
			return ResponseEntity.ok(all);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra uma nova coleção")
	@ApiResponse(responseCode = "201", description = "Coleção cadastrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Colecao.class)))
	@ApiResponse(responseCode = "409", description = "A coleção já se encontra cadastrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ColecaoRegisteringRequest colecaoRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}
		if (this.service.findByNomeAndIdMuseu(colecaoRequest.getNome(), colecaoRequest.getIdMuseu()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A coleção " + colecaoRequest.getNome() + " já está cadastrada");
		}
		if (TipoColecaoEnum.MUSEU.name().equalsIgnoreCase(colecaoRequest.getTipoColecao()) && colecaoRequest.getIdMuseu() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O campo idMuseu não pode ser NULL uma vez que o tipo da coleção é MUSEU");
		}

		Colecao colecao = this.service.create(colecaoRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(colecao);
	}

	@Operation(summary = "Atualiza uma coleção pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Coleção atualizada com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Colecao.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "O nome informado já se encontra cadastrado em outra Coleção", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PutMapping("/{idColecao}")
	public ResponseEntity<Object> update(@PathVariable(name = "idColecao", required = true) UUID idColecao, @RequestBody ColecaoUpdatingRequest colecaoUpdatingRequest) {
		Colecao colecao = this.service.update(colecaoUpdatingRequest);

		return ResponseEntity.ok(colecao);
	}

	@Operation(summary = "Exclui uma coleção pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Coleção excluída com sucesso", content = @Content)
	@DeleteMapping("/{idColecao}")
	public ResponseEntity<Object> delete(@PathVariable(name = "idColecao", required = true) UUID idColecao) {
		this.service.deleteById(idColecao);
		return ResponseEntity.noContent().build();
	}
}
