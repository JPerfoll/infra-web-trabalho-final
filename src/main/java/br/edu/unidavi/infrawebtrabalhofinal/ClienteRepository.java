package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	@Query("SELECT c FROM Cliente c, Endereco e WHERE c.id = e.cliente_id and e.rua = :rua")
	List<Cliente> findByRuaContaining(@Param("rua") String rua);

	@Query("SELECT c FROM Cliente c, Endereco e WHERE c.id = e.cliente_id and e.cidade = :cidade")
	List<Cliente> findByCidadeContaining(@Param("cidade") String cidade);

	@Query("SELECT c FROM Cliente c, Endereco e WHERE c.id = e.cliente_id and e.estado = :estado")
	List<Cliente> findByEstadoContaining(@Param("estado") String estado);
}