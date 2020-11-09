package br.com.danillotparreira.cursomc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;

  public Cidade() {}

  public Cidade(Integer id, String nome, Estado estado) {
    this.id = id;
    this.nome = nome;
    this.estado = estado;
  }

  public Cidade(String nome, Estado estado) {
    this.nome = nome;
    this.estado = estado;
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

  public Estado getEstado() {
    return this.estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public Cidade id(Integer id) {
    this.id = id;
    return this;
  }

  public Cidade nome(String nome) {
    this.nome = nome;
    return this;
  }

  public Cidade estado(Estado estado) {
    this.estado = estado;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Cidade)) {
      return false;
    }
    Cidade cidade = (Cidade) o;
    return Objects.equals(id, cidade.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + "}";
  }
}
