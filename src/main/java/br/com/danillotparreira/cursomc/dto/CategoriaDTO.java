package br.com.danillotparreira.cursomc.dto;

import java.io.Serializable;

import br.com.danillotparreira.cursomc.model.Categoria;

public class CategoriaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String nome;

  public CategoriaDTO() {}

  public CategoriaDTO(Categoria obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
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
}
