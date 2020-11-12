package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;

  @ManyToOne
  @JoinColumn(name = "cidade_id")
  private Cidade cidade;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public Endereco() {}

  public Endereco(
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep,
    Cidade cidade,
    Cliente cliente
  ) {
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cep = cep;
    this.cidade = cidade;
    this.cliente = cliente;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public Cidade getCidade() {
    return this.cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Endereco id(Integer id) {
    this.id = id;
    return this;
  }

  public Endereco logradouro(String logradouro) {
    this.logradouro = logradouro;
    return this;
  }

  public Endereco numero(String numero) {
    this.numero = numero;
    return this;
  }

  public Endereco complemento(String complemento) {
    this.complemento = complemento;
    return this;
  }

  public Endereco bairro(String bairro) {
    this.bairro = bairro;
    return this;
  }

  public Endereco cep(String cep) {
    this.cep = cep;
    return this;
  }

  public Endereco cidade(Cidade cidade) {
    this.cidade = cidade;
    return this;
  }

  public Endereco cliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Endereco)) {
      return false;
    }
    Endereco endereco = (Endereco) o;
    return Objects.equals(id, endereco.id);
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
      getId() +
      "'" +
      ", logradouro='" +
      getLogradouro() +
      "'" +
      ", numero='" +
      getNumero() +
      "'" +
      ", complemento='" +
      getComplemento() +
      "'" +
      ", bairro='" +
      getBairro() +
      "'" +
      ", cep='" +
      getCep() +
      "'" +
      ", cidade='" +
      getCidade() +
      "'" +
      "}"
    );
  }
}
