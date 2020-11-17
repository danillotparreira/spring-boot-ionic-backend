package br.com.danillotparreira.cursomc.services;

import br.com.danillotparreira.cursomc.dto.ClienteDTO;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.services.exceptions.DataIntegrityException;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  public Cliente findById(Integer id) {
    Optional<Cliente> objOptional = repository.findById(id);
    return objOptional.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Cliente.class.getSimpleName()
        )
    );
  }

  public List<Cliente> findAll() {
    return repository.findAll();
  }

  public Page<Cliente> findPage(
    Integer page,
    Integer linesPerPage,
    String orderBy,
    String direction
  ) {
    PageRequest pageRequest = PageRequest.of(
      page,
      linesPerPage,
      Direction.valueOf(direction),
      orderBy
    );
    return repository.findAll(pageRequest);
  }

  public Cliente insert(Cliente obj) {
    return null;
  }

  public Cliente update(Cliente obj) {
    obj = updateData(obj);
    return repository.save(obj);
  }

  public void delete(Integer id) {
    this.findById(id);
    try {
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException(
        "Não é possível excluir uma cliente que possui pedidos."
      );
    }
  }

  public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente()
      .id(objDTO.getId())
      .nome(objDTO.getNome())
      .email(objDTO.getEmail());
  }

  private Cliente updateData(Cliente obj) {
    Cliente find = this.findById(obj.getId());
    return find.nome(obj.getNome()).email(obj.getEmail());
  }
}
