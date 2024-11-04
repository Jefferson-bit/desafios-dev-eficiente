package org.example.cupom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Long> {


    Optional<Cupom> findByCodigoDoCupom(String codigoDoCupom);
}
