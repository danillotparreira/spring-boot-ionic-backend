package br.com.danillotparreira.cursomc.dto;

import br.com.danillotparreira.cursomc.services.validation.ClienteInsert;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty(message = "Preenchimento Obrigatório")
  @Length(
    min = 5,
    max = 120,
    message = "O tamanho deve ser entre 5 a 120 caracteres"
  )
  private String nome;

  @NotEmpty(message = "Preenchimento Obrigatório")
  private String email;

  @NotEmpty(message = "Preenchimento Obrigatório")
  private String cpfOuCnpj;

  private Integer tipo;

  @NotEmpty(message = "Preenchimento Obrigatório")
  private String logradouro;

  private String numero;
  private String complemento;
  private String bairro;

  @NotEmpty(message = "Preenchimento Obrigatório")
  private String cep;

  @NotEmpty(message = "Deve adicionar pelo menos 1 telefone")
  @NotNull(message = "Deve adicionar pelo menos 1 telefone")
  @Size(min = 1, max = 3, message = "Deve conter entre 1 a 3 telefones")
  private List<String> telefones = new ArrayList<>();

  private Integer cidadeId;

  public ClienteNewDTO() {}

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfOuCnpj() {
    return cpfOuCnpj;
  }

  public void setCpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
  }

  public Integer getTipo() {
    return tipo;
  }

  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public List<String> getTelefones() {
    return this.telefones;
  }

  public void setTelefones(List<String> telefones) {
    for (String telefone : telefones) {
      if (telefone != null && !telefone.trim().equals("")) {
        this.telefones.add(telefone);
      }
    }
  }

  public Integer getCidadeId() {
    return cidadeId;
  }

  public ClienteNewDTO nome(String nome) {
    this.nome = nome;
    return this;
  }

  public ClienteNewDTO email(String email) {
    this.email = email;
    return this;
  }

  public ClienteNewDTO cpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
    return this;
  }

  public ClienteNewDTO tipo(Integer tipo) {
    this.tipo = tipo;
    return this;
  }

  public ClienteNewDTO logradouro(String logradouro) {
    this.logradouro = logradouro;
    return this;
  }

  public ClienteNewDTO numero(String numero) {
    this.numero = numero;
    return this;
  }

  public ClienteNewDTO complemento(String complemento) {
    this.complemento = complemento;
    return this;
  }

  public ClienteNewDTO bairro(String bairro) {
    this.bairro = bairro;
    return this;
  }

  public ClienteNewDTO cep(String cep) {
    this.cep = cep;
    return this;
  }
}
