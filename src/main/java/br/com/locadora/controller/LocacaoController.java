package br.com.locadora.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.dto.LocacaoDto;
import br.com.locadora.dto.LocacaoMapper;
import br.com.locadora.model.Locacao;
import br.com.locadora.service.LocacaoService;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {
	@Autowired
	LocacaoService locacaoService;

	@GetMapping
	public ResponseEntity<Page<LocacaoDto>> buscarTodaAsLocacoes(@PageableDefault Pageable pageable) {

		Page<Locacao> locacaoRecebidas = locacaoService.listarTodosAsLocacoes(pageable);
		Page<LocacaoDto> locacaoDtoRecebidas = formatHateos(pageable, locacaoRecebidas);

		return ResponseEntity.ok(locacaoDtoRecebidas);
	}

	@PostMapping
	public ResponseEntity<LocacaoDto> salvarLocacao(@RequestBody @Valid LocacaoDto dto) {
		Locacao locacao = locacaoService.salvarLocacao(LocacaoMapper.fromDto(dto));

		return ResponseEntity.ok(LocacaoMapper.consultaLocacao(locacao));

	}

	@GetMapping("{idPlacaCpf}")
	public ResponseEntity<Page<LocacaoDto>> buscarLocacaoPorIdPlacaCPF(@PageableDefault Pageable pageable,
			@PathVariable(value = "idPlacaCpf") String id) {

		Page<Locacao> locacao = locacaoService.buscarLocacao(pageable, id);
		Page<LocacaoDto> locacaoRecebidas = formatHateos(pageable, locacao);

		return ResponseEntity.ok(locacaoRecebidas);

	}

	private Page<LocacaoDto> formatHateos(Pageable pageable, Page<Locacao> locacao) {
		Page<LocacaoDto> locacaoRecebidas = locacao.map(LocacaoMapper::consultaLocacao);
		for (LocacaoDto locacaoRecebida : locacaoRecebidas) {
			String idRec = locacaoRecebida.getId().toString();
			locacaoRecebida.add(linkTo(methodOn(LocacaoController.class).buscarLocacaoPorIdPlacaCPF(pageable, idRec))
					.withRel("Buscar (GET), Alterar(PUT) ou Excluir(DEL) Locação por ID"));
			String placa = locacaoRecebida.getAutomovel().getPlaca();
			locacaoRecebida.add(linkTo(methodOn(LocacaoController.class).buscarLocacaoPorIdPlacaCPF(pageable, placa))
					.withRel("Buscar (GET) locações por Placa do Automóvel"));
			String cpf = locacaoRecebida.getUsuario().getCpf();
			locacaoRecebida.add(linkTo(methodOn(LocacaoController.class).buscarLocacaoPorIdPlacaCPF(pageable, cpf))
					.withRel("Buscar (GET) locações por CPF do Usuario"));

		}
		return locacaoRecebidas;
	}

	@PutMapping("{id}")
	public ResponseEntity<LocacaoDto> alterarLocacao(@PageableDefault Pageable pageable, @RequestBody LocacaoDto dto,
			@PathVariable Long id) {
		try {
			Locacao locacao = locacaoService.atualizarLocacao(pageable, LocacaoMapper.fromDto(dto), id);

			return ResponseEntity.ok(LocacaoMapper.consultaLocacao(locacao));
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<LocacaoDto> excluirLocacao(@PathVariable Long id) {
		try {
			locacaoService.excluirLocacao(id);

			return ResponseEntity.ok().build();
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}

	}
}
