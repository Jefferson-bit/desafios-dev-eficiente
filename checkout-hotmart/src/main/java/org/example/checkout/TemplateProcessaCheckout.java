package org.example.checkout;

import org.example.cupom.CupomRepository;
import org.example.oferta.OfertaRepository;
import org.example.service.RegistraNovaContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class TemplateProcessaCheckout {

    @Autowired
    private OfertaRepository ofertaRepository;
    @Autowired
    private CupomRepository cupomRepository;
    @Autowired
    private RegistraNovaContaService contaService;

    public void processaCheckout(UUID codigoDaOferta, CheckoutRequest request) {

        var oferta = ofertaRepository.buscaPorOferta(codigoDaOferta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oferta nao encontrada"));

        var conta = contaService.cadastraNovaConta(request.getEmail());

        if (request.getCodigoDoCupom().isPresent()) {
            var cupom = cupomRepository.findByCodigoDoCupom(request.getCodigoDoCupom().get())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "nao existe codigo de cupom para esse produto"));
            cupom.aplicaDesconto(oferta.getPreco());
        }
    }
}
