package br.com.danillotparreira.cursomc.model;

import java.util.Date;

import javax.persistence.Entity;

import br.com.danillotparreira.cursomc.model.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

  private static final long serialVersionUID = 1L;

  private Date dataVencimento;
  private Date dataPagamento;

  public PagamentoComBoleto() {}

  public PagamentoComBoleto(
    EstadoPagamento estadoPagamento,
    Pedido pedido,
    Date dataVencimento,
    Date dataPagamento
  ) {
    super(estadoPagamento, pedido);
    this.dataVencimento = dataVencimento;
    this.dataPagamento = dataPagamento;
  }

  public Date getDataVencimento() {
    return this.dataVencimento;
  }

  public void setDataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public Date getDataPagamento() {
    return this.dataPagamento;
  }

  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

  public PagamentoComBoleto dataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
    return this;
  }

  public PagamentoComBoleto dataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
    return this;
  }

  @Override
  public String toString() {
    return (
      "{" +
      " dataVencimento='" +
      getDataVencimento() +
      "'" +
      ", dataPagamento='" +
      getDataPagamento() +
      "'" +
      "}"
    );
  }
}
