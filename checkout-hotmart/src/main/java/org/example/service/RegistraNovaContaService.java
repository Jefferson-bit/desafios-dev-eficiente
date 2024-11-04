package org.example.service;


import jakarta.transaction.Transactional;
import org.example.configuracao.ConfiguracaoRepository;
import org.example.conta.Conta;
import org.example.conta.ContaRepository;
import org.example.util.OptionalHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/*
    Application Service
 */
@Service
public class RegistraNovaContaService {

    private final ContaRepository contaRepository;
    private final ConfiguracaoRepository configRepository;

    public RegistraNovaContaService(ContaRepository contaRepository, ConfiguracaoRepository configuracaoRepository) {
        this.contaRepository = contaRepository;
        this.configRepository = configuracaoRepository;
    }

    @Transactional
    public Conta cadastraNovaConta(String email) {
        var configuracao = OptionalHttpStatus.execute(configRepository.findByOpcaoPadraoIsTrue(),
                HttpStatus.INTERNAL_SERVER_ERROR, "nao foi possivel identificar uma config padrao");

        return contaRepository.findByEmail(email).orElseGet(() -> contaRepository.save(new Conta(email, configuracao)));
    }
}
