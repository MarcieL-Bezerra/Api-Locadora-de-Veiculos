package br.com.locadora.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
	Page<Automovel> findAll(Pageable pageable);

	Page<Automovel> findByDisponivel(Pageable pageable, Boolean disponivel);

	Optional<Automovel> findByPlaca(String placa);
}
