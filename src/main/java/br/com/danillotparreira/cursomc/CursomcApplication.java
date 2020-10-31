package br.com.danillotparreira.cursomc;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.model.Produto;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;
import br.com.danillotparreira.cursomc.repositories.ProdutoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CursomcApplication.class, args);
  }

  // Configuração da operação de instanciação, com a implementação do CommandLineRunner
  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Override
  public void run(String... args) throws Exception {
    Categoria cat1 = new Categoria("Informática");
    Categoria cat2 = new Categoria("Escritório");

    Produto p1 = new Produto("Computador", 2000.00);
    Produto p2 = new Produto("Impressora", 800.00);
    Produto p3 = new Produto("Mouse", 80.00);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().addAll(Arrays.asList(p2));

    p1.getCategorias().addAll(Arrays.asList(cat1));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p3.getCategorias().addAll(Arrays.asList(cat1));

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
  }
}
