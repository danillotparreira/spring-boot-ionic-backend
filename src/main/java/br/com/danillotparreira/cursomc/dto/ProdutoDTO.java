package br.com.danillotparreira.cursomc.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.danillotparreira.cursomc.model.Produto;

public class ProdutoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String nome;
  private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco();
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

    public ProdutoDTO id(Integer id) {
        this.id = id;
        return this;
    }

    public ProdutoDTO nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoDTO preco(Double preco) {
        this.preco = preco;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProdutoDTO)) {
            return false;
        }
        ProdutoDTO produtoDTO = (ProdutoDTO) o;
        return Objects.equals(id, produtoDTO.id) && Objects.equals(nome, produtoDTO.nome) && Objects.equals(preco, produtoDTO.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", preco='" + getPreco() + "'" +
            "}";
    }
}