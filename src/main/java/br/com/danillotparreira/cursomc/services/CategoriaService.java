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
  private CategoriaRepository categoriaRepository;

  public List<Categoria> findAll() {
    return categoriaRepository.findAll();
  }

  public Categoria findById(Integer id) {
    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
    return categoriaOptional.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Categoria.class.getSimpleName()
        )
    );
  }

  public Categoria save(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }
}
