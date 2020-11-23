package br.com.danillotparreira.cursomc.services.exceptions;

public class TelefonException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TelefonException(String msg) {
    super(msg);
  }

  public TelefonException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
