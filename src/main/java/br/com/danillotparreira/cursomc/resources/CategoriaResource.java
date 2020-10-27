package br.com.danillotparreira.cursomc.resources;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.services.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaResource {
  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    Categoria categoria = categoriaService.findById(id);
    return ResponseEntity.ok(categoria);
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(categoriaService.findAll());
  }
}
