package br.edu.unidavi.infrawebtrabalhofinal;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class EnderecoResource extends Resource<Endereco> {
	
	public EnderecoResource(Endereco endereco, Link... links) {
		super(endereco, links);
	}

}