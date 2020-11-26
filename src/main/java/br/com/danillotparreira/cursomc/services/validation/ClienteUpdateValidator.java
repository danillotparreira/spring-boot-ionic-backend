package br.com.danillotparreira.cursomc.services.validation;

import br.com.danillotparreira.cursomc.dto.ClienteDTO;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.resources.exeptions.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator
  implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

  @Autowired
  public ClienteRepository repository;

  @Autowired
  public HttpServletRequest request;

  @Override
  public boolean isValid(
    ClienteDTO objDTO,
    ConstraintValidatorContext context
  ) {
    List<FieldMessage> list = new ArrayList<>();

    isEmailValid(list, objDTO.getEmail(), idCliente());

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context
        .buildConstraintViolationWithTemplate(e.getMessage())
        .addPropertyNode(e.getFieldName())
        .addConstraintViolation();
    }

    return list.isEmpty();
  }

  private Integer idCliente() {
    Map<String, String> map = (Map<String, String>) request.getAttribute(
      HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
    );
    Integer idCliente = Integer.parseInt(map.get("id"));
    return idCliente;
  }


  private void isEmailValid(List<FieldMessage> list, String email, Integer id) {
    Cliente cliente = repository.findByEmail(email);
    if (cliente != null && !cliente.getId().equals(id)) {
      list.add(new FieldMessage("email", "Este email já está cadastrado"));
    }
  }
}
