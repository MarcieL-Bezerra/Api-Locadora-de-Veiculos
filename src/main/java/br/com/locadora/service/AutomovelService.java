package br.com.locadora.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.locadora.exception.EntityNotFoundException;
import br.com.locadora.model.Automovel;
import br.com.locadora.repository.AutomovelRepository;

@Service
public class AutomovelService {
	AutomovelRepository automovelRepository;

	public AutomovelService(AutomovelRepository automovelRepository) {
		this.automovelRepository = automovelRepository;

	}

	public Automovel salvarAutomovel(Automovel automovel) {

		return automovelRepository.save(automovel);
	}

	public Page<Automovel> listarTodosOsAutomoveis(Pageable pageable) {

		return automovelRepository.findAll(pageable);

	}

	public Page<Automovel> listarTodosOsAutomoveisDisponiveis(Pageable pageable) {

		return automovelRepository.findByDisponivel(pageable, true);

	}

	public Automovel buscarAutomovel(String id) {
		if (id.length() == 7) {
			return buscarAutomovelPorPlaca(id);
		}
		return buscarAutomovelPorId(Long.parseLong(id));
	}

	public Automovel buscarAutomovelPorPlaca(String placa) {
		Optional<Automovel> optional = automovelRepository.findByPlaca(placa);

		return optional.orElseThrow(() -> new EntityNotFoundException("Automovel não encontrado"));
	}

	public Automovel buscarAutomovelPorId(Long id) {
		Optional<Automovel> optional = automovelRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Automovel não encontrado"));
	}

	public Automovel atualizarAutomovel(Automovel Automovel, Long id) {

		Automovel AutomovelOriginal = this.buscarAutomovelPorId(id);

		Automovel.setId(AutomovelOriginal.getId());

		return automovelRepository.save(Automovel);
	}

	public ResponseEntity<String> excluirAutomovel(Long id) {
		try {
			Automovel automovelOriginal = this.buscarAutomovelPorId(id);
			automovelRepository.delete(automovelOriginal);

			return new ResponseEntity<>("Automovel excluído", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Automovel não pode ser excluído, Verifique sua existência ou relações!",
					HttpStatus.BAD_REQUEST);
		}

	}

}
