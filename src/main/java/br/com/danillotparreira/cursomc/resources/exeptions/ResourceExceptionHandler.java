package br.com.danillotparreira.cursomc.resources.exeptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.danillotparreira.cursomc.services.exceptions.DataIntegrityException;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
    ObjectNotFoundException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError(
      status.value(),
      e.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DataIntegrityException.class)
  public ResponseEntity<StandardError> dataIntegrity(
    DataIntegrityException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
      status.value(),
      e.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(status).body(err);
  }
}
