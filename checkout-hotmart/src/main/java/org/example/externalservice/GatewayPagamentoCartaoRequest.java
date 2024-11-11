package org.example.externalservice;

import org.example.checkout.cartaodecredito.CartaoDeCreditoRequest;
import org.example.compra.InfoCompraCartao;

import java.math.BigDecimal;

public record GatewayPagamentoCartaoRequest(
        String numeroDoCartao,
        String nomeDoTitular,
        Integer anoDoVencimento,
        Integer mesDoVencimento,
        BigDecimal valorParcela,
        Integer numerosDeParcelas) {


    public GatewayPagamentoCartaoRequest(InfoCompraCartao infoCompraCartao) {
        this(infoCompraCartao.getNumeroDoCartao(),
                infoCompraCartao.getNomeDoTitular(),
                infoCompraCartao.getAnoDoVencimento(),
                infoCompraCartao.getMesDoVencimento(),
                infoCompraCartao.getValorDaParcela(),
                infoCompraCartao.getNumerosDeParcelas());
    }
}

