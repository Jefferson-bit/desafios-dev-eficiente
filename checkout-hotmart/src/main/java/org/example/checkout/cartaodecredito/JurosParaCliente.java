package org.example.checkout.cartaodecredito;

import org.example.configuracao.Configuracao;
import org.example.oferta.Oferta;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JurosParaCliente implements CalculoJuros {

    @Override
    public BigDecimal calcularJuros(Oferta oferta, Configuracao configuracao) {
        var i = configuracao.getTaxaDeJuros().divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_EVEN);
        var fatorJurosComposto = BigDecimal.ONE.add(i).pow(oferta.getNumerosDeParcelas());
        var numerador = oferta.getPreco().multiply(fatorJurosComposto).multiply(i);
        var denominador = fatorJurosComposto.subtract(BigDecimal.ONE);
        return numerador.divide(denominador, 2, RoundingMode.HALF_EVEN);
    }
}
