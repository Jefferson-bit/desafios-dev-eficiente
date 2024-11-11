package org.example.checkout.cartaodecredito;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.checkout.TemplateFluxoCheckout;
import org.example.externalservice.GatewayCartaoDeCredito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartaoDeCreditoController {

    private final TemplateFluxoCheckout templateFluxoCheckout;
    private final GatewayCartaoDeCredito gatewayCartao;
    private final EntityManager entityManager;

    public CartaoDeCreditoController(TemplateFluxoCheckout templateFluxoCheckout, GatewayCartaoDeCredito gatewayCartao, EntityManager entityManager) {
        this.templateFluxoCheckout = templateFluxoCheckout;
        this.gatewayCartao = gatewayCartao;
        this.entityManager = entityManager;
    }

    @PostMapping("/cartaodecreditos/{codigoDaOferta}")
    @Transactional
    public ResponseEntity<?> efetuaPagamentoComCartao(@RequestBody @Valid CartaoDeCreditoRequest request,
                                                      @PathVariable("codigoDaOferta") UUID codigoDaOferta) {

        var compraBuilder = templateFluxoCheckout.processaCheckout(codigoDaOferta, request);
        var compra = compraBuilder.comCartao(request);
//        gatewayCartao.processaPagamento(compra).subscribe();

        entityManager.persist(compra);

        return ResponseEntity.accepted().build();
    }

}
