package org.example.produto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {



    Optional<Produto> findByNome(String nome);
}
