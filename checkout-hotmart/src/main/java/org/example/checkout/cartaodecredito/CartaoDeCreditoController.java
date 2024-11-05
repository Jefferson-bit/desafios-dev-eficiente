package org.example.checkout.cartaodecredito;


import jakarta.validation.Valid;
import org.example.checkout.TemplateFluxoCheckout;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class CartaoDeCreditoController {

    private final TemplateFluxoCheckout templateFluxoCheckout;

    public CartaoDeCreditoController(TemplateFluxoCheckout templateFluxoCheckout) {
        this.templateFluxoCheckout = templateFluxoCheckout;
    }

    @PostMapping("/cartaodecreditos/{codigoDaOferta}")
    public void efetuaPagamento(@RequestBody @Valid CartaoDeCreditoRequest request,
                                @PathVariable("codigoDaOferta") UUID codigoDaOferta) {

        var compra = templateFluxoCheckout.processaCheckout(codigoDaOferta, request);

        List<BigDecimal> bigDecimals = compra.getOferta().getPagadorDeJuros()
                .calculaJuros(
                        compra.getOferta().getPreco(),
                        compra.getConta().getConfiguracao().getTaxaDeJuros(),
                        compra.getConta().getConfiguracao().getTaxaDeComissao(),
                        request.getNumerosDeParcelas());
        // chama gateway de pagamento



    }
}
