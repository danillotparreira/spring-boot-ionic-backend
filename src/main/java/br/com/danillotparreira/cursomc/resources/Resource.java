package br.com.danillotparreira.cursomc.resources;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface Resource<T, DTO, PK> {
  public ResponseEntity<List<DTO>> findAll();

  public ResponseEntity<Page<DTO>> findPage(
    Integer page,
    Integer linesPerPage,
    String orderBy,
    String direction
  );

  public ResponseEntity<T> findById(PK id);

  public ResponseEntity<Void> insert(DTO objDTO);

  public ResponseEntity<Void> update(DTO objDTO, PK id);

  public ResponseEntity<Void> delete(PK id);
}
