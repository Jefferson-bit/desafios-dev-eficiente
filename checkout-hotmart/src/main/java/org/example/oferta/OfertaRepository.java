package org.example.oferta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {


    @Query(value = """
            SELECT * FROM OFERTA
             WHERE CODIGO_GLOBAL = ?1 OR (PRINCIPAL = TRUE AND
            NOT EXISTS(
            SELECT 1 FROM OFERTA
             WHERE CODIGO_GLOBAL = ?1))
            ORDER BY
             CASE WHEN CODIGO_GLOBAL = ?1 THEN 0 ELSE 1 END
            """, nativeQuery = true)
    Optional<Oferta> buscaPorOferta(UUID codigoGlobal);

    Optional<Oferta> findByPrincipalTrue();

}
