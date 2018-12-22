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

import br.edu.unidavi.infrawebtrabalhofinal.Item;
import br.edu.unidavi.infrawebtrabalhofinal.ItemRepository;
import br.edu.unidavi.infrawebtrabalhofinal.ItemResource;
import br.edu.unidavi.infrawebtrabalhofinal.ItemResourceAssembler;

@RestController
@RequestMapping("/items")
public class ItemRestController {

	@Autowired
	ItemRepository repository;
	
	ItemResourceAssembler assembler = new ItemResourceAssembler();
	
	@PostConstruct
	public void init() {
	
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ItemResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<ItemResource> get(@PathVariable Long id) {
		Item item = repository.findOne(id);
		if (item != null) {			
			return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<ItemResource> create(@RequestBody Item item) {
		item = repository.save(item);
		if (item != null) {
			return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<ItemResource> update(@PathVariable Long id, @RequestBody Item item) {
		if (item != null) {
			item.setId(id);
			item = repository.save(item);
			return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<ItemResource> delete(@PathVariable Long id) {
		Item item = repository.findOne(id);
		if (item != null) {
			repository.delete(item);
			return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}