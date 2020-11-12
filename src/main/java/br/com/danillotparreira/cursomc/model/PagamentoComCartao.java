package br.com.danillotparreira.cursomc.model;

import javax.persistence.Entity;

import br.com.danillotparreira.cursomc.model.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

  private static final long serialVersionUID = 1L;

  private Integer numeroDeParcelas;

  public PagamentoComCartao() {}

  public PagamentoComCartao(
    EstadoPagamento estadoPagamento,
    Pedido pedido,
    Integer numeroDeParcelas
  ) {
    super(estadoPagamento, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public Integer getNumeroDeParcelas() {
    return this.numeroDeParcelas;
  }

  public void setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public PagamentoComCartao numeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
    return this;
  }

  @Override
  public String toString() {
    return "{" + " numeroDeParcelas='" + getNumeroDeParcelas() + "'" + "}";
  }
}
