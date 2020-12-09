package br.com.danillotparreira.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.danillotparreira.cursomc.model.Pedido;

public interface EmailService {

     void sendOrderConfimationEmail(Pedido obj);

     void sendEmail(SimpleMailMessage msg);
}
