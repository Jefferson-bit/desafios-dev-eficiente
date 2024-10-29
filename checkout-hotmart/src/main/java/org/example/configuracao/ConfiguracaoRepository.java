package org.example.configuracao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracaoRepository extends CrudRepository<Configuracao, Long> {
    Optional<Configuracao> findByOpcaoPadraoIsTrue();
}
