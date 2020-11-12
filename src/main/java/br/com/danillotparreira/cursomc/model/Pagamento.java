package br.com.danillotparreira.cursomc.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import br.com.danillotparreira.cursomc.model.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  private Integer estadoPagamento;

  @OneToOne
  @JoinColumn(name = "pedido_id")
  @MapsId
  private Pedido pedido;

  public Pagamento() {}

  public Pagamento(EstadoPagamento estadoPagamento, Pedido pedido) {
    this.estadoPagamento = estadoPagamento.getCod();
    this.pedido = pedido;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EstadoPagamento getEstadoPagamento() {
    return EstadoPagamento.toEnum(this.estadoPagamento);
  }

  public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
    this.estadoPagamento = estadoPagamento.getCod();
  }

  public Pedido getPedido() {
    return this.pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public Pagamento id(Integer id) {
    this.id = id;
    return this;
  }

  public Pagamento estadoPagamento(EstadoPagamento estadoPagamento) {
    this.estadoPagamento = estadoPagamento.getCod();
    return this;
  }

  public Pagamento pedido(Pedido pedido) {
    this.pedido = pedido;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Pagamento)) {
      return false;
    }
    Pagamento pagamento = (Pagamento) o;
    return Objects.equals(id, pagamento.id);
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
      ", estadoPagamento='" +
      getEstadoPagamento() +
      "'" +
      ", pedido='" +
      getPedido() +
      "'" +
      "}"
    );
  }
}
