package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidavi.infrawebtrabalhofinal.Endereco;
import br.edu.unidavi.infrawebtrabalhofinal.EnderecoRepository;
import br.edu.unidavi.infrawebtrabalhofinal.EnderecoResource;
import br.edu.unidavi.infrawebtrabalhofinal.EnderecoResourceAssembler;

@RestController
@RequestMapping("/enderecos")
public class EnderecoRestController {

	@Autowired
	EnderecoRepository repository;
	
	EnderecoResourceAssembler assembler = new EnderecoResourceAssembler();
	
	@PostConstruct
	public void init() {

		repository.save(new Endereco(1l, "Beco Arnoldo Molinari", "Rio do Sul", "SC", "89160334", 1l));
		repository.save(new Endereco(2l, "Rua Teste", "Rio do Sul", "PR", "895623332", 2l));
		repository.save(new Endereco(3l, "Rua XV de Novembro", "Rio do Sul", "SC", "89160033", 3l));
		repository.save(new Endereco(4l, "Avenida Oscar Barcelos", "Rio do Sul", "SC", "89520114", 4l));
		repository.save(new Endereco(5l, "Beco Arnoldo Molinari", "Rio do Sul", "SC", "89160334", 5l));
		
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<EnderecoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
		Endereco endereco = repository.findOne(id);
		if (endereco != null) {			
			return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<EnderecoResource> create(@RequestBody Endereco endereco) {
		endereco = repository.save(endereco);
		if (endereco != null) {
			return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<EnderecoResource> update(@PathVariable Long id, @RequestBody Endereco endereco) {
		if (endereco != null) {
			endereco.setId(id);
			endereco = repository.save(endereco);
			return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<EnderecoResource> delete(@PathVariable Long id) {
		Endereco endereco = repository.findOne(id);
		if (endereco != null) {
			repository.delete(endereco);
			return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}