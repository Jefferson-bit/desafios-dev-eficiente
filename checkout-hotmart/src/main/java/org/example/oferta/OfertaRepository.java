package org.example.oferta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {


    Optional<Oferta> findByCodigoGlobal(UUID codigoGlobal);
    Optional<Oferta> findByPrincipalTrue();

}
