package br.com.danillotparreira.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danillotparreira.cursomc.model.Pedido;
import br.com.danillotparreira.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos", produces = "application/json")
public class PedidoResource {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    Pedido pedido = pedidoService.findById(id);
    return ResponseEntity.ok(pedido);
  }
}
