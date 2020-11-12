package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.danillotparreira.cursomc.model.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;

  @JsonManagedReference
  @OneToMany(mappedBy = "cliente")
  private List<Endereco> enderecos = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "TELEFONE")
  private Set<String> telefones = new HashSet<>();

  @OneToMany(mappedBy = "cliente")
  @JsonBackReference
  private List<Pedido> pedidos = new ArrayList<>();

  public Cliente() {}

  public Cliente(
    String nome,
    String email,
    String cpfOuCnpj,
    TipoCliente tipo
  ) {
    this.nome = nome;
    this.email = email;
    this.cpfOuCnpj = cpfOuCnpj;
    this.tipo = tipo.getCod();
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfOuCnpj() {
    return this.cpfOuCnpj;
  }

  public void setCpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
  }

  public Integer getTipo() {
    return this.tipo;
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public List<Endereco> getEnderecos() {
    return this.enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public Set<String> getTelefones() {
    return this.telefones;
  }

  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  public List<Pedido> getPedidos() {
    return this.pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  public Cliente nome(String nome) {
    this.nome = nome;
    return this;
  }

  public Cliente email(String email) {
    this.email = email;
    return this;
  }

  public Cliente cpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
    return this;
  }

  public Cliente tipo(Integer tipo) {
    this.tipo = tipo;
    return this;
  }

  public Cliente enderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
    return this;
  }

  public Cliente enderecos(Endereco... enderecos) {
    this.enderecos.addAll(Arrays.asList(enderecos));
    return this;
  }

  public Cliente endereco(Endereco endereco) {
    this.enderecos.add(endereco);
    return this;
  }

  public Cliente telefones(String... telefones) {
    this.telefones.addAll(Arrays.asList(telefones));
    return this;
  }

  public Cliente pedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
    return this;
  }

  public Cliente pedidos(Pedido... pedidos) {
    this.pedidos.addAll(Arrays.asList(pedidos));
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Cliente)) {
      return false;
    }
    Cliente cliente = (Cliente) o;
    return Objects.equals(id, cliente.id);
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
      ", nome='" +
      getNome() +
      "'" +
      ", email='" +
      getEmail() +
      "'" +
      ", cpfOuCnpj='" +
      getCpfOuCnpj() +
      "'" +
      ", tipo='" +
      getTipo() +
      "'" +
      ", enderecos='" +
      getEnderecos() +
      "'" +
      "}"
    );
  }
}
