package org.example.produto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {



    Optional<Produto> findByCodigoGlobal(UUID nome);
}
