package br.com.danillotparreira.cursomc.services;

import br.com.danillotparreira.cursomc.dto.CategoriaDTO;
import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;
import br.com.danillotparreira.cursomc.services.exceptions.DataIntegrityException;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  public List<Categoria> findAll() {
    return repository.findAll();
  }

  public Page<Categoria> findPage(
    Integer page,
    Integer linesPerPage,
    String orderBy,
    String direction
  ) {
    PageRequest pageRequest = PageRequest.of(
      page,
      linesPerPage,
      Direction.valueOf(direction),
      orderBy
    );
    return repository.findAll(pageRequest);
  }

  public Categoria findById(Integer id) {
    Optional<Categoria> obj = repository.findById(id);
    return obj.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Categoria.class.getSimpleName()
        )
    );
  }

  public Categoria insert(Categoria obj) {
    return obj.getId() == null ? repository.save(obj) : null;
  }

  public Categoria update(Categoria obj) {
    this.findById(obj.getId());
    return repository.save(obj);
  }

  public void delete(Integer id) {
    this.findById(id);
    try {
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException(
        "Não é possível excluir uma categoria que possui produtos."
      );
    }
  }

  public Categoria fromDTO(CategoriaDTO objDTO) {
    return new Categoria(objDTO.getId(), objDTO.getNome());
  }
}
