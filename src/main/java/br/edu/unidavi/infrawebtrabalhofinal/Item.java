package br.edu.unidavi.infrawebtrabalhofinal;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@IdClass(ChaveComposta.class)
public class Item {
    @Id
    Long pedido_id;
    @Id
    Long produto_id;
    Integer quantidade;
    Double total;
}