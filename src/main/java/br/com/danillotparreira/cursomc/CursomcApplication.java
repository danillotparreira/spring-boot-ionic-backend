package br.com.danillotparreira.cursomc;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.model.Cidade;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.model.Endereco;
import br.com.danillotparreira.cursomc.model.Estado;
import br.com.danillotparreira.cursomc.model.ItemPedido;
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
import br.com.danillotparreira.cursomc.repositories.ItemPedidoRepository;
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

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Override
  public void run(String... args) throws Exception {
    Categoria cat1 = new Categoria("Informática");
    Categoria cat2 = new Categoria("Escritório");
    Categoria cat3 = new Categoria("Cama Mesa e Banho");
    Categoria cat4 = new Categoria("Eletrônicos");
    Categoria cat5 = new Categoria("Jardinagem");
    Categoria cat6 = new Categoria("Decoração");
    Categoria cat7 = new Categoria("Perfumaria");
    Categoria cat8 = new Categoria("Veículos");
    Categoria cat9 = new Categoria("Animais");
    Categoria cat10 = new Categoria("Cozinha");

    Produto p1 = new Produto("Computador", 2000.00);
    Produto p2 = new Produto("Impressora", 800.00);
    Produto p3 = new Produto("Mouse", 80.00);
    Produto p4 = new Produto("Mesa de Escritório", 300.00);
    Produto p5 = new Produto("Toalha", 50.00);
    Produto p6 = new Produto("Colcha", 200.00);
    Produto p7 = new Produto("TV true color", 1200.00);
    Produto p8 = new Produto("Roçadeira", 800.00);
    Produto p9 = new Produto("Abajour", 100.00);
    Produto p10 = new Produto("Pendente", 180.00);
    Produto p11 = new Produto("Shampoo", 90.00);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().addAll(Arrays.asList(p2, p4));
    cat3.getProdutos().addAll(Arrays.asList(p5, p6));
    cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
    cat5.getProdutos().addAll(Arrays.asList(p8));
    cat6.getProdutos().addAll(Arrays.asList(p9, p10));
    cat7.getProdutos().addAll(Arrays.asList(p11));

    p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
    p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
    p4.getCategorias().addAll(Arrays.asList(cat2));
    p5.getCategorias().addAll(Arrays.asList(cat3));
    p6.getCategorias().addAll(Arrays.asList(cat3));
    p7.getCategorias().addAll(Arrays.asList(cat4));
    p8.getCategorias().addAll(Arrays.asList(cat5));
    p9.getCategorias().addAll(Arrays.asList(cat6));
    p10.getCategorias().addAll(Arrays.asList(cat6));
    p11.getCategorias().addAll(Arrays.asList(cat7));

    categoriaRepository.saveAll(
      Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10)
    );
    produtoRepository.saveAll(
      Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
    );

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

    ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
    ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
    ped1.getItens().addAll(Arrays.asList(ip1, ip2));
    p1.getItens().addAll(Arrays.asList(ip1));
    p3.getItens().addAll(Arrays.asList(ip2));

    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
    ped2.getItens().addAll(Arrays.asList(ip3));
    p2.getItens().addAll(Arrays.asList(ip3));

    itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
  }
}
