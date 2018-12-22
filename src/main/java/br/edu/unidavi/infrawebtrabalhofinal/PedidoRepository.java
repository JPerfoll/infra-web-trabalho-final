package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT p FROM Pedido p WHERE p.dataCriacao = :data")
	List<Pedido> findByDataCriacao(@Param("data") Date data);
}