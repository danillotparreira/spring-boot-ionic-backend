package br.com.danillotparreira.cursomc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double desconto;
  private Integer quantidade;
  private Double preco;

  public ItemPedido() {}

  public ItemPedido(
    Pedido pedido,
    Produto produto,
    Double desconto,
    Integer quantidade,
    Double preco
  ) {
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

  public Produto getProduto() {
    return this.id.getProduto();
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
    if (o == this) return true;
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
    return (
      "{" +
      " Produto='" +
      getProduto() +
      " Pedido='" +
      getPedido() +
      "'" +
      ", desconto='" +
      getDesconto() +
      "'" +
      ", quantidade='" +
      getQuantidade() +
      "'" +
      ", preco='" +
      getPreco() +
      "'" +
      "}"
    );
  }
}
