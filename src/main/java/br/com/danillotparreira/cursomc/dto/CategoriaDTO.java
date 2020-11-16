package br.com.danillotparreira.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.danillotparreira.cursomc.model.Categoria;

public class CategoriaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty(message = "Preenchimento do nome obrigatório")
  @Length(
    min = 5,
    max = 80,
    message = "Tamanho do nome deve ser entre 5 a 80 caracteres."
  )
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
