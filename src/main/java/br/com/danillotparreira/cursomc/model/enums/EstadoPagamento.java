package br.com.danillotparreira.cursomc.model.enums;

import java.util.Arrays;

public enum EstadoPagamento {
  PENDENTE(1, "Pendente"),
  QUITADO(2, "Quitado"),
  CANCELADO(3, "Cancelado");

  private Integer cod;
  private String descricao;

  private EstadoPagamento(Integer cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public Integer getCod() {
    return this.cod;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public static EstadoPagamento toEnum(Integer cod) {
    return Arrays
      .stream(EstadoPagamento.values())
      .filter(e -> e.getCod().equals(cod))
      .findFirst()
      .orElseThrow(
        () -> new IllegalStateException(String.format("Id inválido: %s.", cod))
      );
  }
}
