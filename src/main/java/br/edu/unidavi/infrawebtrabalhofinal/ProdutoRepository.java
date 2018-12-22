package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByDescricaoContaining(@Param("descricao") String descricao);

    List<Produto> findByMarca(@Param("marca") String marca);
}