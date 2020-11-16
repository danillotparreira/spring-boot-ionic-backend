package br.com.danillotparreira.cursomc.resources;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.services.CategoriaService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    Categoria categoria = service.findById(id);
    return ResponseEntity.ok(categoria);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
    obj = service.insert(obj);
    if(obj == null){
      return ResponseEntity.badRequest().build();
    }
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(obj.getId())
      .toUri();

    return ResponseEntity.created(uri).build();
  }
}
