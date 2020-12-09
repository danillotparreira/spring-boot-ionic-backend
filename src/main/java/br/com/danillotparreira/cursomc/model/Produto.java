package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;
  private Double preco;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "PRODUTO_CATEGORIA",
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id")
  )
  private List<Categoria> categorias = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "id.produto")
  private Set<ItemPedido> itens = new HashSet<>();

  public Produto() {}

  public Produto(String nome, Double preco) {
    this.nome = nome;
    this.preco = preco;
  }

  @JsonIgnore
  public List<Pedido> getPedidos() {
    List<Pedido> lista = new ArrayList<>();
    itens.forEach(x -> lista.add(x.getPedido()));
    return lista;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getPreco() {
    return this.preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public List<Categoria> getCategorias() {
    return this.categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  public Set<ItemPedido> getItens() {
    return this.itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  public Produto id(Integer id) {
    this.id = id;
    return this;
  }

  public Produto nome(String nome) {
    this.nome = nome;
    return this;
  }

  public Produto preco(Double preco) {
    this.preco = preco;
    return this;
  }

  public Produto categorias(List<Categoria> categorias) {
    this.categorias = categorias;
    return this;
  }

  public Produto addCategoria(Categoria... categoria) {
    this.categorias.addAll(Arrays.asList(categoria));
    return this;
  }

  public Produto itens(Set<ItemPedido> itens) {
    this.itens = itens;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Produto)) {
      return false;
    }
    Produto produto = (Produto) o;
    return Objects.equals(id, produto.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return (
      "{" +
      " id='" +
      id +
      "'" +
      ", nome='" +
      nome +
      "'" +
      ", preco='" +
      preco +
      "'" +
      "}"
    );
  }
}
