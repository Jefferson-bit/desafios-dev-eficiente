package org.example.checkout.cartaodecredito;

import org.example.configuracao.Configuracao;
import org.example.oferta.Oferta;

import java.math.BigDecimal;

public interface CalculoJuros {

    BigDecimal calcularJuros(Oferta oferta, Configuracao configuracao);

}
