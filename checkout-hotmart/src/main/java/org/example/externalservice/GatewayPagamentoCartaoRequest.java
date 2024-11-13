package org.example.externalservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.compra.InfoCompraCartao;

import java.math.BigDecimal;

public record GatewayPagamentoCartaoRequest(
        @JsonProperty("numeroDoCartao")
        String numeroDoCartao,
        @JsonProperty("nomeDoTitular")
        String nomeDoTitular,
        @JsonProperty("anoDoVencimento")
        Integer anoDoVencimento,
        @JsonProperty("mesDoVencimento")
        Integer mesDoVencimento,
        @JsonProperty("valorParcela")
        BigDecimal valorParcela,
        @JsonProperty("numerosDeParcelas")
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

