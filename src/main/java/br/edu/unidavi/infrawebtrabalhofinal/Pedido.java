package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
public class Pedido {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	Long numero;
	Double total;
	
	@DateTimeFormat(iso=ISO.DATE)
	Date dataCriacao;
}