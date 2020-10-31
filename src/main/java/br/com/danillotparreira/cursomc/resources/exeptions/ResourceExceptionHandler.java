package br.com.danillotparreira.cursomc.resources.exeptions;

import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
