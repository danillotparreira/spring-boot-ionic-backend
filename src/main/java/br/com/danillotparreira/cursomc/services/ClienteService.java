package br.com.danillotparreira.cursomc.services;

import br.com.danillotparreira.cursomc.model.Categoria;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public Cliente findById(Integer id) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    return clienteOptional.orElseThrow(
      () ->
        new ObjectNotFoundException(
          "Objeto não encontrado! Id " +
          id +
          ", Tipo: " +
          Cliente.class.getSimpleName()
        )
    );
  }

  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }
}
