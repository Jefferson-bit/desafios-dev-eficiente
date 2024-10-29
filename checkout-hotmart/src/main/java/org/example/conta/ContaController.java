package org.example.conta;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.configuracao.ConfiguracaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ContaController {

    private final ContaRepository contaRepository;
    private final ConfiguracaoRepository configRepository;

    public ContaController(ContaRepository repository, ConfiguracaoRepository configRepository) {
        this.contaRepository = repository;
        this.configRepository = configRepository;
    }

    @PostMapping("/contas")
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ContaRequest request) {
        var configuracao = configRepository.findByOpcaoPadraoIsTrue().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "nao foi possivel identificar uma config padrao"));
        var entity = request.toEntity(configuracao);

        contaRepository.save(entity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).body(entity.getCodigoGlobal());
    }
}
