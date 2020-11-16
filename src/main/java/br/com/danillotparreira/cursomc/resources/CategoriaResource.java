package br.com.danillotparreira.cursomc.resources;

import br.com.danillotparreira.cursomc.dto.CategoriaDTO;
import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    List<CategoriaDTO> listDTO = list
      .stream()
      .map(obj -> new CategoriaDTO(obj))
      .collect(Collectors.toList());
    return ResponseEntity.ok(listDTO);
  }

  @GetMapping("/page")
  public ResponseEntity<Page<CategoriaDTO>> findPage(
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction.toUpperCase());
    Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
    return ResponseEntity.ok(listDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
    Categoria categoria = service.findById(id);
    return ResponseEntity.ok(categoria);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
    if (objDTO.getId() != null) {
      return ResponseEntity.badRequest().build();
    }
    Categoria obj = service.fromDTO(objDTO);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(obj.getId())
      .toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@Valid 
    @RequestBody CategoriaDTO objDTO,
    @PathVariable Integer id
  ) {
    Categoria obj = service.fromDTO(objDTO);
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
