package br.com.danillotparreira.cursomc.resource;

import br.com.danillotparreira.cursomc.model.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaResource {

  @GetMapping
  public List<Categoria> listar() {
    Categoria cat1 = new Categoria(1, "Informática");
    Categoria cat2 = new Categoria(2, "Escritório");
    List<Categoria> categorias = new ArrayList<>();
    categorias.add(cat1);
    categorias.add(cat2);
    return categorias;
  }
}
