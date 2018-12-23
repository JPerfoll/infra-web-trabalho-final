package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidavi.infrawebtrabalhofinal.Produto;
import br.edu.unidavi.infrawebtrabalhofinal.ProdutoRepository;
import br.edu.unidavi.infrawebtrabalhofinal.ProdutoResource;
import br.edu.unidavi.infrawebtrabalhofinal.ProdutoResourceAssembler;

@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {

	@Autowired
	ProdutoRepository repository;
	
	ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();
	
	@PostConstruct
	public void init() {
	
		repository.save(new Produto(1l, "Produto 1", "Marca 1", 5.99));
		repository.save(new Produto(2l, "Produto 2", "Marca 2", 17.99));
		repository.save(new Produto(3l, "Produto 3", "Marca 3", 24.00));
		repository.save(new Produto(4l, "Produto 4", "Marca 4", 55.00));
		repository.save(new Produto(5l, "Produto 5", "Marca 5", 98.50));
		
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ProdutoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
		Produto produto = repository.findOne(id);
		if (produto != null) {			
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
		produto = repository.save(produto);
		if (produto != null) {
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
		if (produto != null) {
			produto.setId(id);
			produto = repository.save(produto);
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
		Produto produto = repository.findOne(id);
		if (produto != null) {
			repository.delete(produto);
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<ProdutoResource>> findByDescricao(@PathVariable String descricao) {
		return new ResponseEntity<>(assembler.toResources(repository.findByDescricaoContaining(descricao)), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
		return new ResponseEntity<>(assembler.toResources(repository.findByMarca(marca)), HttpStatus.OK);
	}
}