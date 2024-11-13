package org.example.checkout.boleto;


import jakarta.validation.Valid;
import org.example.checkout.TemplateFluxoCheckout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BoletoController {


    private final TemplateFluxoCheckout templateFluxoCheckout;


    public BoletoController(TemplateFluxoCheckout templateFluxoCheckout) {
        this.templateFluxoCheckout = templateFluxoCheckout;
    }


    @PostMapping("/boletos/{codigoDaOferta}")
    public ResponseEntity<?> efetuaPagamentoComCartao(@RequestBody @Valid BoletoRequest request,
                                                      @PathVariable("codigoDaOferta") UUID codigoDaOferta) {

        var compraBuilder = templateFluxoCheckout.processaCheckout(codigoDaOferta, request);
        compraBuilder.comBoleto(request);

        return ResponseEntity.accepted().body("Pedido aceito. Enviaremos um e-mail com mais informações");

    }
}
