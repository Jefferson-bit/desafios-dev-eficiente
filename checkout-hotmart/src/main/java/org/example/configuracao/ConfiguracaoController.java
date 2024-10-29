package org.example.configuracao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ConfiguracaoController {

    private final ConfiguracaoRepository configRepository;

    public ConfiguracaoController(ConfiguracaoRepository configRepository) {
        this.configRepository = configRepository;
    }

    @PostMapping("/configuracoes")
    @Transactional
    public void cadastraConfiguracao(@RequestBody @Valid ConfiguracaoRequest request) {
        if (configRepository.findByOpcaoPadraoIsTrue().isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe uma opcao padrao cadastrada");
        }
        configRepository.save(request.toEntity());
    }
}
