package br.edu.unidavi.infrawebtrabalhofinal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String descricao;
    String marca;
    Double valor;
}