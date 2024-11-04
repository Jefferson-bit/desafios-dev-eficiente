package org.example.checkout.cartaodecredito;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.checkout.TemplateProcessaCheckout;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartaoDeCreditoController {

    private final TemplateProcessaCheckout processaCheckout;

    public CartaoDeCreditoController(TemplateProcessaCheckout processaCheckout) {
        this.processaCheckout = processaCheckout;
    }

    @PostMapping("/cartaodecreditos/{codigoDaOferta}")
    @Transactional
    public void test(@RequestBody @Valid CartaoDeCreditoRequest request,
                     @PathVariable("codigoDaOferta") UUID codigoDaOferta) {
        processaCheckout.processaCheckout(codigoDaOferta, request);
    }
}
