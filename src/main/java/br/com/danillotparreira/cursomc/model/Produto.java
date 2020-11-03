package br.com.danillotparreira.cursomc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Produto implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;
  private Double preco;

  @JsonBackReference
  @ManyToMany
  @JoinTable(
    name = "PRODUTO_CATEGORIA",
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id")
  )
  private List<Categoria> categorias = new ArrayList<>();

  public Produto() {}

  public Produto(String nome, Double preco) {
    this.nome = nome;
    this.preco = preco;
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

  public Produto addCategoria(Categoria categoria) {
    this.categorias.add(categoria);
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
