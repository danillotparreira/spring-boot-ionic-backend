package br.com.danillotparreira.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danillotparreira.cursomc.model.Pedido;
import br.com.danillotparreira.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos", produces = "application/json")
public class PedidoResource {

  @Autowired
  private PedidoService service;

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    Pedido obj = service.findById(id);
    return ResponseEntity.ok(obj);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(obj.getId())
      .toUri();

    return ResponseEntity.created(uri).build();
  }
}
