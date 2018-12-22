package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByRua(String rua);
	List<Endereco> findByCidade(String cidade);
	List<Endereco> findByEstado(String estado);
}