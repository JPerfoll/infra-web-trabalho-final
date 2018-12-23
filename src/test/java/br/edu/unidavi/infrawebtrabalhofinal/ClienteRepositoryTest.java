package br.edu.unidavi.infrawebtrabalhofinal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClienteRepository repository;

    @Test
    public void salvarCliente() throws Exception {
        Cliente cliente = Cliente.builder()       
            .nome("Rodrigo")
            .email("rodrigo@unidavi.edu.br")
            .cpf("085.654.321.45")
            .dataNascimento(new Date("03/02/1986")).build();

        cliente = repository.save(cliente);

        assertNotNull(cliente);
        assertTrue(cliente.getId() != null);        
    }

}