package br.com.danillotparreira.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.danillotparreira.cursomc.dto.ClienteNewDTO;
import br.com.danillotparreira.cursomc.model.enums.TipoCliente;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.resources.exeptions.FieldMessage;
import br.com.danillotparreira.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator
  implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

  @Autowired
  public ClienteRepository repository;

  @Override
  public boolean isValid(
    ClienteNewDTO objDTO,
    ConstraintValidatorContext context
  ) {
    List<FieldMessage> list = new ArrayList<>();

    isTelefoneValid(list, objDTO.getTelefones());
    isCpfOuCnpjValid(list, objDTO.getCpfOuCnpj(), objDTO.getTipo());
    isEmailValid(list, objDTO.getEmail());


    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context
        .buildConstraintViolationWithTemplate(e.getMessage())
        .addPropertyNode(e.getFieldName())
        .addConstraintViolation();
    }

    return list.isEmpty();
  }

  private void isCpfOuCnpjValid(
    List<FieldMessage> list,
    String cpfOuCnpj,
    Integer tipo
  ) {
    if (
      tipo.equals(TipoCliente.PESSOA_FISICA.getCod()) &&
      !BR.isValidCPF(cpfOuCnpj)
    ) {
      list.add(
        new FieldMessage("cpfOuCnpj", "CPF " + cpfOuCnpj + " é Inválido")
      );
    } else if (
      tipo.equals(TipoCliente.PESSOA_JURIDICA.getCod()) &&
      !BR.isValidCNPJ(cpfOuCnpj)
    ) {
      list.add(
        new FieldMessage("cpfOuCnpj", "CNPJ " + cpfOuCnpj + " é Inválido")
      );
    }
  }

  /**
   * Verifica se a lista de telefones contém telefone ou uma lista em vazia.
   * @param telefones
   * @return true caso tenha ao menos 1 telefone que não esteja em branco.
   */
  private void isTelefoneValid(
    List<FieldMessage> list,
    List<String> telefones
  ) {
    if (telefones.isEmpty()) {
      list.add(
        new FieldMessage(
          "telefones",
          "É obrigatório o preenchimento de ao menos 1 telefone"
        )
      );
    }
  }
  
  private void isEmailValid(List<FieldMessage> list, String email){
    if (repository.findByEmail(email) != null){
      list.add(new FieldMessage("email", "Este email já está cadastrado"));
    }
  }
}
