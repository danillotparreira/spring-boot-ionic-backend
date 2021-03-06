package br.com.danillotparreira.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.danillotparreira.cursomc.model.PagamentoComBoleto;

@Service
public class BoletoService {

  public void preencherPagamentoComBoleto(
    PagamentoComBoleto pagto,
    Date instante
  ) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(instante);
    calendar.add(Calendar.DAY_OF_MONTH, 7);
    pagto.setDataVencimento(calendar.getTime());
  }
}
