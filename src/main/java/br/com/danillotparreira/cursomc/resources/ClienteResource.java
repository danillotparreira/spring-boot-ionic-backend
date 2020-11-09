package br.com.danillotparreira.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes", produces = "application/json")
public class ClienteResource {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    Cliente cliente = clienteService.findById(id);
    return ResponseEntity.ok(cliente);
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(clienteService.findAll());
  }
}
