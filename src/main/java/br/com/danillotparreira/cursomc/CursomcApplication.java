package br.com.danillotparreira.cursomc;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.model.Cidade;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.model.Endereco;
import br.com.danillotparreira.cursomc.model.Estado;
import br.com.danillotparreira.cursomc.model.Pagamento;
import br.com.danillotparreira.cursomc.model.PagamentoComBoleto;
import br.com.danillotparreira.cursomc.model.PagamentoComCartao;
import br.com.danillotparreira.cursomc.model.Pedido;
import br.com.danillotparreira.cursomc.model.Produto;
import br.com.danillotparreira.cursomc.model.enums.EstadoPagamento;
import br.com.danillotparreira.cursomc.model.enums.TipoCliente;
import br.com.danillotparreira.cursomc.repositories.CategoriaRepository;
import br.com.danillotparreira.cursomc.repositories.CidadeRepository;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.repositories.EnderecoRepository;
import br.com.danillotparreira.cursomc.repositories.EstadoRepository;
import br.com.danillotparreira.cursomc.repositories.PagamentoRepository;
import br.com.danillotparreira.cursomc.repositories.PedidoRepository;
import br.com.danillotparreira.cursomc.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
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

  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private PagamentoRepository pagamentoRepository;

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

    Estado est1 = new Estado("Minas Gerais");
    Estado est2 = new Estado("São Paulo");

    Cidade c1 = new Cidade("Uberlândia", est1);
    Cidade c2 = new Cidade("São Paulo", est2);
    Cidade c3 = new Cidade("Campinas", est2);

    est1.getCidades().addAll(Arrays.asList(c1));
    est2.getCidades().addAll(Arrays.asList(c2, c3));

    estadoRepository.saveAll(Arrays.asList(est1, est2));
    cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

    Cliente cli1 = new Cliente(
      "Maria Silva",
      "maria@gmail.com",
      "36378912377",
      TipoCliente.PESSOA_FISICA
    );
    cli1.telefones("27363323", "98838393");

    Endereco e1 = new Endereco(
      "Rua Flores",
      "300",
      "Apt 303",
      "Jardim",
      "38220834",
      c1,
      cli1
    );
    Endereco e2 = new Endereco(
      "Avenida Matos",
      "105",
      "Sala 800",
      "Centro",
      "38777012",
      c2,
      cli1
    );
    cli1.enderecos(e1, e2);

    clienteRepository.save(cli1);
    enderecoRepository.saveAll(Arrays.asList(e1, e2));

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    Pedido ped1 = new Pedido(sdf.parse("30/09/2020 10:32"), cli1, e1);
    Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
    ped1.pagamento(pagto1);

    Pedido ped2 = new Pedido(sdf.parse("10/10/2020 19:35"), cli1, e2);
    Pagamento pagto2 = new PagamentoComBoleto(
      EstadoPagamento.PENDENTE,
      ped2,
      sdf.parse("20/10/2020 00:00"),
      null
    );
    ped2.pagamento(pagto2);

    cli1.pedidos(ped1, ped2);
    pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

    pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
  }
}
