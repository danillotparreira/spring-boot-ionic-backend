package br.com.danillotparreira.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danillotparreira.cursomc.model.ItemPedido;
import br.com.danillotparreira.cursomc.model.PagamentoComBoleto;
import br.com.danillotparreira.cursomc.model.Pedido;
import br.com.danillotparreira.cursomc.model.Produto;
import br.com.danillotparreira.cursomc.model.enums.EstadoPagamento;
import br.com.danillotparreira.cursomc.repositories.ItemPedidoRepository;
import br.com.danillotparreira.cursomc.repositories.PagamentoRepository;
import br.com.danillotparreira.cursomc.repositories.PedidoRepository;
import br.com.danillotparreira.cursomc.repositories.ProdutoRepository;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository repository;

  @Autowired
  private BoletoService boletoService;

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  public Pedido findById(Integer id) {
    Optional<Pedido> pedidoOptional = repository.findById(id);
    return pedidoOptional.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Pedido.class.getSimpleName()
        )
    );
  }

  public Pedido insert(Pedido obj) {
    obj.setInstante(new Date());
    obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
    obj.getPagamento().setPedido(obj);

    if (obj.getPagamento() instanceof PagamentoComBoleto) {
      PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
      boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
    }

    obj = repository.save(obj);

    pagamentoRepository.save(obj.getPagamento());

    for (ItemPedido item : obj.getItens()) {
      item.setDesconto(0.0);
      Optional<Produto> produto = produtoRepository.findById(item.getProduto().getId());
      item.setPreco(
        produto.get().getPreco()
      );
      item.setPedido(obj);
    }
    itemPedidoRepository.saveAll(obj.getItens());
    return obj;
  }
}
