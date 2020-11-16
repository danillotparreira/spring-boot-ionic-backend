package br.com.danillotparreira.cursomc.resources.exeptions;

import br.com.danillotparreira.cursomc.services.exceptions.DataIntegrityException;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    String message = e.getMessage();
    StandardError err = new StandardError(
      status.value(),
      message,
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
    String message = e.getMessage();
    StandardError err = new StandardError(
      status.value(),
      message,
      System.currentTimeMillis()
    );
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validation(
    MethodArgumentNotValidException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = "Erro de Validação";
    ValidationError err = new ValidationError(
      status.value(),
      message,
      System.currentTimeMillis()
    );
    e
      .getBindingResult()
      .getFieldErrors()
      .forEach(x -> err.addErrors(x.getField(), x.getDefaultMessage()));
    return ResponseEntity.status(status).body(err);
  }
}
