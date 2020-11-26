package br.com.danillotparreira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.model.Produto;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;
import br.com.danillotparreira.cursomc.repositories.ProdutoRepository;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository repository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  public Produto findById(Integer id) {
    Optional<Produto> ProdutoOptional = repository.findById(id);
    return ProdutoOptional.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Produto.class.getSimpleName()
        )
    );
  }

  public Page<Produto> search(
    String nome,
    List<Integer> ids,
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
    List<Categoria> categorias = categoriaRepository.findAllById(ids);
    return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
  }
}
