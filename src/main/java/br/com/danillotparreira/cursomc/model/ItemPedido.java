package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double desconto;
  private Integer quantidade;
  private Double preco;

  public ItemPedido() {
  }

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    this.id.pedido(pedido).produto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  public double getSubTotal() {
    return (this.preco - this.desconto) * this.quantidade;
  }

  @JsonIgnore
  public Pedido getPedido() {
    return this.id.getPedido();
  }

  public void setPedido(Pedido pedido) {
    this.id.setPedido(pedido);
  }

  public Produto getProduto() {
    return this.id.getProduto();
  }

  public void setProduto(Produto produto) {
    this.id.setProduto(produto);
  }

  public Double getDesconto() {
    return this.desconto;
  }

  public void setDesconto(Double desconto) {
    this.desconto = desconto;
  }

  public Integer getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getPreco() {
    return this.preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public ItemPedido id(ItemPedidoPK id) {
    this.id = id;
    return this;
  }

  public ItemPedido desconto(Double desconto) {
    this.desconto = desconto;
    return this;
  }

  public ItemPedido quantidade(Integer quantidade) {
    this.quantidade = quantidade;
    return this;
  }

  public ItemPedido preco(Double preco) {
    this.preco = preco;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof ItemPedido)) {
      return false;
    }
    ItemPedido itemPedido = (ItemPedido) o;
    return Objects.equals(id, itemPedido.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    StringBuilder builder = new StringBuilder();
    builder.append(getProduto().getNome());
    builder.append(", Qtd: ").append(getQuantidade());
    builder.append(", Preço unitário: ").append(nf.format(getPreco()));
    builder.append(", Subtotal: ").append(nf.format(getSubTotal())).append("\n");
    return builder.toString();
  }
}
