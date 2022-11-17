package br.com.locadora.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Automovel;
import br.com.locadora.model.Locacao;
import br.com.locadora.model.Usuario;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
	Page<Locacao> findAll(Pageable pageable);

	Page<Locacao> findById(Pageable pageable, Long id);

	Page<Locacao> findByAutomovel(Pageable pageable, Automovel automovel);

	Page<Locacao> findByUsuario(Pageable pageable, Usuario usuario);

}
