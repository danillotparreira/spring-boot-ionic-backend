package br.com.danillotparreira.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danillotparreira.cursomc.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
