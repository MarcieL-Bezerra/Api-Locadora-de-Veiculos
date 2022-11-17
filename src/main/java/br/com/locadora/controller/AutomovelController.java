package br.com.locadora.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.dto.AutomovelDto;
import br.com.locadora.dto.AutomovelMapper;
import br.com.locadora.model.Automovel;
import br.com.locadora.service.AutomovelService;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {
	@Autowired
	AutomovelService automovelService;

	@GetMapping
	public ResponseEntity<Page<AutomovelDto>> buscarTodaOsAutomoveis(@PageableDefault Pageable pageable) {
		Page<AutomovelDto> autoRecebidos = automovelService.listarTodosOsAutomoveis(pageable)
				.map(AutomovelMapper::consultaAutomovel);
		for (AutomovelDto autoRecebido : autoRecebidos) {
			String id = autoRecebido.getId().toString();
			autoRecebido.add(linkTo(methodOn(AutomovelController.class).buscarAutomovel(pageable, id)).withRel("Buscar (GET), Alterar(PUT) ou Excluir(DEL) Automóvel por ID"));
			String placa = autoRecebido.getPlaca();
			autoRecebido
					.add(linkTo(methodOn(AutomovelController.class).buscarAutomovel(pageable, placa)).withRel("Buscar (GET) Automóvel por Placa"));

		}

		return ResponseEntity.ok(autoRecebidos);
	}

	@GetMapping
	@RequestMapping("/disponiveis")
	public ResponseEntity<Page<AutomovelDto>> buscarTodaOsAutomoveisDisponivel(@PageableDefault Pageable pageable) {
		Page<AutomovelDto> autoRecebidos = automovelService.listarTodosOsAutomoveisDisponiveis(pageable)
				.map(AutomovelMapper::consultaAutomovel);
		for (AutomovelDto autoRecebido : autoRecebidos) {
			String id = autoRecebido.getId().toString();
			autoRecebido.add(linkTo(methodOn(AutomovelController.class).buscarAutomovel(pageable, id)).withRel("Buscar (GET), Alterar(PUT) ou Excluir(DEL) Automóvel por ID"));
			String placa = autoRecebido.getPlaca();
			autoRecebido
					.add(linkTo(methodOn(AutomovelController.class).buscarAutomovel(pageable, placa)).withRel("Buscar (GET) Automóvel por Placa"));

		}

		return ResponseEntity.ok(autoRecebidos);
	}

	@PostMapping
	public ResponseEntity<AutomovelDto> salvarAutomovel(@RequestBody @Valid AutomovelDto dto) {
		Automovel automovel = automovelService.salvarAutomovel(AutomovelMapper.fromDto(dto));

		return ResponseEntity.ok(AutomovelMapper.consultaAutomovel(automovel));

	}

	@GetMapping("{idCpfPlaca}")
	public ResponseEntity<AutomovelDto> buscarAutomovel(@PageableDefault Pageable pageable,
			@PathVariable(value = "idCpfPlaca") String id) {

		Automovel automovel = automovelService.buscarAutomovel(id);

		AutomovelDto autoDto = AutomovelMapper.consultaAutomovel(automovel);
		autoDto.add(linkTo(methodOn(AutomovelController.class).buscarTodaOsAutomoveis(pageable))
				.withRel("Lista de Automóveis"));
		autoDto.add(linkTo(methodOn(AutomovelController.class).buscarTodaOsAutomoveisDisponivel(pageable))
				.withRel("Lista de Automóveis Disponíveis"));
		autoDto.add(linkTo(methodOn(AutomovelController.class).alterarAutomovel(pageable,autoDto, autoDto.getId()))
				.withRel("Buscar (GET), Alterar(PUT) ou Excluir(DEL) Automóvel por ID"));
		return ResponseEntity.ok(autoDto);

	}

	@PutMapping("{id}")
	public ResponseEntity<AutomovelDto> alterarAutomovel(@PageableDefault Pageable pageable,
			@RequestBody AutomovelDto dto, @PathVariable Long id) {
		try {
			Automovel automovel = automovelService.atualizarAutomovel(AutomovelMapper.fromDto(dto), id);
			AutomovelDto autoDto = AutomovelMapper.consultaAutomovel(automovel);
			autoDto.add(linkTo(methodOn(AutomovelController.class).buscarTodaOsAutomoveis(pageable))
					.withRel("Lista de Automóveis"));
			return ResponseEntity.ok(autoDto);
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> excluirAutomovel(@PathVariable Long id) {
		try {

			automovelService.excluirAutomovel(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
