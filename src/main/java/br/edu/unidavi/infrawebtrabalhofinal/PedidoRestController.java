package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.Date;
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

import br.edu.unidavi.infrawebtrabalhofinal.Pedido;
import br.edu.unidavi.infrawebtrabalhofinal.PedidoRepository;
import br.edu.unidavi.infrawebtrabalhofinal.PedidoResource;
import br.edu.unidavi.infrawebtrabalhofinal.PedidoResourceAssembler;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

	@Autowired
	PedidoRepository repository;
	
	PedidoResourceAssembler assembler = new PedidoResourceAssembler();
	
	@PostConstruct
	public void init() {
	
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<PedidoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResource> get(@PathVariable Long id) {
		Pedido pedido = repository.findOne(id);
		if (pedido != null) {			
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {
		pedido = repository.save(pedido);
		if (pedido != null) {
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<PedidoResource> update(@PathVariable Long id, @RequestBody Pedido pedido) {
		if (pedido != null) {
			pedido.setId(id);
			pedido = repository.save(pedido);
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<PedidoResource> delete(@PathVariable Long id) {
		Pedido pedido = repository.findOne(id);
		if (pedido != null) {
			repository.delete(pedido);
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/datacriacao/{data}")
	public ResponseEntity<List<PedidoResource>> findByNome(@PathVariable Date data) {
		return new ResponseEntity<>(assembler.toResources(repository.findByDataCriacao(data)), HttpStatus.OK);
	}
}