package br.com.locadora.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.locadora.model.Automovel;
import br.com.locadora.model.Locacao;
import br.com.locadora.model.Usuario;
import br.com.locadora.repository.LocacaoRepository;

@Service
public class LocacaoService {
	LocacaoRepository locacaoRepository;
	AutomovelService automovelService;
	UsuarioService usuarioService;

	public LocacaoService(LocacaoRepository locacaoRepository, AutomovelService automovelService,
			UsuarioService usuarioService) {
		this.locacaoRepository = locacaoRepository;
		this.automovelService = automovelService;
		this.usuarioService = usuarioService;

	}

	public Locacao salvarLocacao(Locacao locacao) {
		validaAutoeUser(locacao);
		Integer diasLocados = trataDatas(locacao);
		trataValorLocacao(locacao, diasLocados);
		Locacao locacaoSalva = locacaoRepository.save(locacao);

		if (locacaoSalva.getAutomovel().getId() != null) {
			Automovel automovel = automovelService.buscarAutomovelPorId(locacaoSalva.getAutomovel().getId());
			locacaoSalva.setAutomovel(automovel);
			automovel.setDisponivel(false);
			automovelService.atualizarAutomovel(automovel, locacaoSalva.getAutomovel().getId());
		}
		if (locacaoSalva.getUsuario().getId() != null) {
			Usuario usuario = usuarioService.buscarUsuario(locacaoSalva.getUsuario().getId().toString());
			locacaoSalva.setUsuario(usuario);
		}

		return locacaoSalva;
	}

	public Page<Locacao> listarTodosAsLocacoes(Pageable pageable) {

		return locacaoRepository.findAll(pageable);

	}

	public Page<Locacao> buscarLocacao(Pageable pageable, String id) {
		if (id.length() == 11) {
			return buscarLocacaoPorUsuario(pageable, id);
		} else if (id.length() == 7) {
			return buscarLocacaoPorAutomovel(pageable, id);
		}

		return buscarLocacaoPorId(pageable, Long.parseLong(id));
	}

	public Page<Locacao> buscarLocacaoPorId(Pageable pageable, Long id) {
		Page<Locacao> locacao = locacaoRepository.findById(pageable, id);

		return locacao;
	}

	public Page<Locacao> buscarLocacaoPorUsuario(Pageable pageable, String id) {
		Usuario user = usuarioService.buscarUsuario(id);
		Page<Locacao> locacao = locacaoRepository.findByUsuario(pageable, user);

		return locacao;
	}

	public Page<Locacao> buscarLocacaoPorAutomovel(Pageable pageable, String id) {
		Automovel auto = automovelService.buscarAutomovel(id);
		Page<Locacao> locacao = locacaoRepository.findByAutomovel(pageable, auto);

		return locacao;
	}

	public Locacao atualizarLocacao(Pageable pageable, Locacao locacao, Long id) {
		validaAutoeUser(locacao);
		Optional<Locacao> locacaoOriginal = locacaoRepository.findById(id);
		locacao.setDataLocacao(locacaoOriginal.get().getDataLocacao());
		locacao.setId(locacaoOriginal.get().getId());

		FormataDatas formataDatas = new FormataDatas();
		Integer diasLocados = formataDatas.CalculaQtdDias(locacao.getDataLocacao(), locacao.getDataDevolucao());

		trataValorLocacao(locacao, diasLocados);

		return locacaoRepository.save(locacao);
	}

	public ResponseEntity<String> excluirLocacao(Long id) {
		try {
			Optional<Locacao> locacaoOriginal = locacaoRepository.findById(id);
			locacaoRepository.delete(locacaoOriginal.get());

			return new ResponseEntity<>("Locacao excluído", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Locacao não pode ser excluído, Verifique sua existência ou relações!",
					HttpStatus.BAD_REQUEST);
		}

	}

	private void validaAutoeUser(Locacao locacao) {
		Automovel autoEncontrado = new Automovel();
		Usuario usuarioEncontrado = new Usuario();

		// Valida caso sejam enviados um automovel ou um usuario inexistentes
		if (locacao.getAutomovel().getId() != null) {
			autoEncontrado = automovelService.buscarAutomovel(locacao.getAutomovel().getId().toString());
			autoEncontrado.setDisponivel(false);
			automovelService.atualizarAutomovel(autoEncontrado, autoEncontrado.getId());

		}
		if (locacao.getUsuario().getId() != null) {
			usuarioEncontrado = usuarioService.buscarUsuario(locacao.getUsuario().getId().toString());

		}
		locacao.setAutomovel(autoEncontrado);
		locacao.setUsuario(usuarioEncontrado);
	}

	private Integer trataDatas(Locacao locacao) {
		FormataDatas formataDatas = new FormataDatas();
		locacao.setDataLocacao(formataDatas.RetornaDataAtual());
		locacao.setDataDevolucao(formataDatas.ComparaDatas(locacao.getDataLocacao(), locacao.getDataDevolucao()));
		Integer diasLocados = formataDatas.CalculaQtdDias(locacao.getDataLocacao(), locacao.getDataDevolucao());
		return diasLocados;
	}

	private void trataValorLocacao(Locacao locacao, Integer diasLocados) {
		CalculaLocacao locacaoCervice = new CalculaLocacao();
		BigDecimal ValorLocacaoDia = locacao.getAutomovel().getValorDiaria();
		locacao.setValorLocacao(locacaoCervice.calculaValorLocacao(diasLocados, ValorLocacaoDia));
	}

}
