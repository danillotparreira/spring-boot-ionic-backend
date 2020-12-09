package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date instante;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega")
  private Endereco enderecoDeEntrega;

  @OneToMany(mappedBy = "id.pedido")
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido() {
  }

  public Pedido(Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
    this.instante = instante;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public double getValorTotal() {
    return this.itens.stream().mapToDouble(item -> item.getSubTotal()).sum();
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getInstante() {
    return this.instante;
  }

  public void setInstante(Date instante) {
    this.instante = instante;
  }

  public Pagamento getPagamento() {
    return this.pagamento;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Endereco getEnderecoDeEntrega() {
    return this.enderecoDeEntrega;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public Set<ItemPedido> getItens() {
    return this.itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  public Pedido id(Integer id) {
    this.id = id;
    return this;
  }

  public Pedido instante(Date instante) {
    this.instante = instante;
    return this;
  }

  public Pedido pagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
    return this;
  }

  public Pedido cliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  public Pedido enderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
    return this;
  }

  public Pedido itens(Set<ItemPedido> itens) {
    this.itens = itens;
    return this;
  }

  public Pedido itens(ItemPedido... itens) {
    this.itens.addAll(Arrays.asList(itens));
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Pedido)) {
      return false;
    }
    Pedido pedido = (Pedido) o;
    return Objects.equals(id, pedido.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    StringBuilder builder = new StringBuilder();
    builder.append("Pedido número: ").append(getId());
    builder.append(", Instante: ").append(sdf.format(getInstante()));
    builder.append(", Cliente: ").append(getCliente().getNome());
    builder.append(", Situação do Pagamento: ").append(getPagamento().getEstadoPagamento().getDescricao());
    builder.append("\nDetalhes:\n");
    for (ItemPedido itemPedido : itens) {
      builder.append(itemPedido.toString());
    }
    builder.append("Valor Total: ").append(nf.format(getValorTotal()));
    return builder.toString();
  }
}
