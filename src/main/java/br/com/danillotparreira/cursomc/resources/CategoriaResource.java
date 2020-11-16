package br.com.danillotparreira.cursomc.resources;

import br.com.danillotparreira.cursomc.dto.CategoriaDTO;
import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<List<CategoriaDTO>> findAll() {
    List<Categoria> list = service.findAll();
    List<CategoriaDTO> listDto = list
      .stream()
      .map(obj -> new CategoriaDTO(obj))
      .collect(Collectors.toList());
    return ResponseEntity.ok(listDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
    Categoria categoria = service.findById(id);
    return ResponseEntity.ok(categoria);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
    if (obj.getId() != null) {
      return ResponseEntity.badRequest().build();
    }
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(obj.getId())
      .toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @RequestBody Categoria obj,
    @PathVariable Integer id
  ) {
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
