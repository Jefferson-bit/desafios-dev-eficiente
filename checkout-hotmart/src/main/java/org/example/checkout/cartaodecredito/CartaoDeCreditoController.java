package org.example.checkout.cartaodecredito;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.compra.Compra;
import org.example.configuracao.Configuracao;
import org.example.conta.Conta;
import org.example.conta.ContaRepository;
import org.example.oferta.Oferta;
import org.example.oferta.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class CartaoDeCreditoController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private OfertaRepository ofertaRepository;

    @PostMapping("/cartaodecreditos/{codigoDaOferta}")
    @Transactional
    public void test(@RequestBody @Valid CartaDeCreditoRequest request,
                     @PathVariable("codigoDaOferta") UUID codigoDaOferta) {

        var oferta = ofertaRepository.buscaPorOferta(codigoDaOferta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "erro inesperado"));

        Compra compraEntity = request.toEntity(oferta);

        Conta conta = contaRepository.findByEmail(request.getEmail())
                        .get();
        Configuracao configuracao = conta.getConfiguracao();


        compraEntity.processaCheckout(oferta.getPreco(), oferta.getPrincipal());

    }
}
