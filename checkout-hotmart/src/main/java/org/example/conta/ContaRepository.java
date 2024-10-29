package org.example.conta;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {


    Optional<Conta> findByCodigoGlobal(UUID codigoGlobal);
}
