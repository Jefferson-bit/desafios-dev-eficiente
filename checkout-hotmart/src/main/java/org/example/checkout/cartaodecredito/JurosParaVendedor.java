package org.example.checkout.cartaodecredito;

import org.example.configuracao.Configuracao;
import org.example.oferta.Oferta;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JurosParaVendedor implements CalculoJuros {


    @Override
    public BigDecimal calcularJuros(Oferta oferta, Configuracao configuracao) {
        var valorParcela = oferta.getPreco().divide(BigDecimal.valueOf(oferta.getNumerosDeParcelas()), RoundingMode.HALF_UP);
        // ---
        var comissao = oferta.getPreco().multiply(configuracao.getTaxaDeComissao()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        var valorLiquido = oferta.getPreco().subtract(comissao);
        //
        System.out.printf("O valor de cada parcela (sem juros) para o cliente é: R$ %.2f%n", valorParcela);
        System.out.printf("O valor líquido recebido pelo vendedor após a comissão é: R$ %.2f%n", valorLiquido);
        return valorParcela;
    }
}
