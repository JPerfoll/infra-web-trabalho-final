package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i, Pedido p WHERE i.pedido_id = :pedido")
    List<Item> findByPedidoId(@Param("pedido") Long pedido_id);
}