package br.edu.unidavi.infrawebtrabalhofinal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.dataCriacao = :data")
	List<Pedido> findByDataCriacao(@Param("data") Date data);

}