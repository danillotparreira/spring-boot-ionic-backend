package br.com.danillotparreira.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> findAll() {
    return categoriaRepository.findAll();
  }

  public Categoria findById(Integer id) {
    return categoriaRepository.findById(id).orElse(null);
  }

  public Categoria save(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }
}
