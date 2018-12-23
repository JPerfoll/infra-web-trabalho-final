package br.edu.unidavi.infrawebtrabalhofinal;

import java.util.Date;
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

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired
	ClienteRepository repository;
	
	ClienteResourceAssembler assembler = new ClienteResourceAssembler();
	
	@PostConstruct
	public void init() {
		
		repository.save(new Cliente(1l, "Jean", "jeanperfoll@gmail.com", "085.999.454-56", new Date("17/05/1993")));
		repository.save(new Cliente(2l, "Cristina", "cristina@gmail.com", "085.999.454-56", new Date("24/07/1963")));
		repository.save(new Cliente(3l, "Laura", "laura@gmail.com", "085.999.454-56", new Date("07/02/2009")));
		repository.save(new Cliente(4l, "João", "joao@gmail.com", "085.999.454-56", new Date("09/11/1960")));
		repository.save(new Cliente(5l, "Jaqueline", "jaqueline@gmail.com", "085.999.454-56", new Date("07/11/1989")));
	
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ClienteResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
		Cliente cliente = repository.findOne(id);
		if (cliente != null) {			
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
		cliente = repository.save(cliente);
		if (cliente != null) {
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		if (cliente != null) {
			cliente.setId(id);
			cliente = repository.save(cliente);
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
		Cliente cliente = repository.findOne(id);
		if (cliente != null) {
			repository.delete(cliente);
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ClienteResource>> findByNome(@PathVariable String nome) {
		return new ResponseEntity<>(assembler.toResources(repository.findByNome(nome)), HttpStatus.OK);
	}

	@Secured("ROLE_USER")
	@GetMapping("/rua/{rua}")
	public ResponseEntity<List<ClienteResource>> findByRua(@PathVariable String rua) {
		return new ResponseEntity<>(assembler.toResources(repository.findByRuaContaining(rua)), HttpStatus.OK);
	}

	@Secured("ROLE_USER")
	@GetMapping("/cidade/{cidade}")
	public ResponseEntity<List<ClienteResource>> findByCidade(@PathVariable String cidade) {
		return new ResponseEntity<>(assembler.toResources(repository.findByCidadeContaining(cidade)), HttpStatus.OK);
	}

	@Secured("ROLE_USER")
	@GetMapping("/estado/{estado}")
	public ResponseEntity<List<ClienteResource>> findByEstado(@PathVariable String estado) {
		return new ResponseEntity<>(assembler.toResources(repository.findByEstadoContaining(estado)), HttpStatus.OK);
	}
}