package br.com.danillotparreira.cursomc.services;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  public List<Categoria> findAll() {
    return repository.findAll();
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
}
