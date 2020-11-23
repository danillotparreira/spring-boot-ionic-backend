package br.com.danillotparreira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danillotparreira.cursomc.dto.ClienteDTO;
import br.com.danillotparreira.cursomc.dto.ClienteNewDTO;
import br.com.danillotparreira.cursomc.model.Cidade;
import br.com.danillotparreira.cursomc.model.Cliente;
import br.com.danillotparreira.cursomc.model.Endereco;
import br.com.danillotparreira.cursomc.model.enums.TipoCliente;
import br.com.danillotparreira.cursomc.repositories.ClienteRepository;
import br.com.danillotparreira.cursomc.repositories.EnderecoRepository;
import br.com.danillotparreira.cursomc.services.exceptions.DataIntegrityException;
import br.com.danillotparreira.cursomc.services.exceptions.ObjectNotFoundException;
import br.com.danillotparreira.cursomc.services.exceptions.TelefonException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private EnderecoRepository enderecoRepository;

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

  @Transactional
  public Cliente insert(Cliente obj) {
    obj = repository.save(obj);
    enderecoRepository.saveAll(obj.getEnderecos());
    return obj;
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

  public Cliente fromDTO(ClienteNewDTO objDTO) {
    Cliente cliente = new Cliente(
      objDTO.getNome(),
      objDTO.getEmail(),
      objDTO.getCpfOuCnpj(),
      TipoCliente.toEnum(objDTO.getTipo())
    );
    Cidade cidade = new Cidade().id(objDTO.getCidadeId());
    Endereco endereco = new Endereco(
      objDTO.getLogradouro(),
      objDTO.getNumero(),
      objDTO.getComplemento(),
      objDTO.getBairro(),
      objDTO.getCep(),
      cidade,
      cliente
    );
    cliente.endereco(endereco);
    verificaSeExisteTelefone(objDTO.getTelefones());
    cliente.telefones(objDTO.getTelefones());
    return cliente;
  }
  
  private Cliente updateData(Cliente obj) {
    Cliente find = this.findById(obj.getId());
    return find.nome(obj.getNome()).email(obj.getEmail());
  }
  
  private void verificaSeExisteTelefone(String... telefones){
    int quantidadeTelefone = 0;
    for (String fone : telefones) {
      if (fone == null || fone.trim() == "") quantidadeTelefone++;
    }
    if (telefones.length == quantidadeTelefone) throw new TelefonException(
      "É necessário pelo menos 1 telefone para contato."
    );
  }
}
