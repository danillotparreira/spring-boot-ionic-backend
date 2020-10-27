package br.com.danillotparreira.cursomc.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias", produces = "application/json")
public class CategoriaResource {

  @GetMapping
  public String listar() {
    return "Rest testado.";
  }
}
