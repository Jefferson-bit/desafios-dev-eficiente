package org.example.externalservice;


import org.example.checkout.cartaodecredito.CartaoDeCreditoRequest;
import org.example.compra.Compra;
import org.example.compra.InfoCompraCartao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class GatewayCartaoDeCredito {

    private final WebClient webClient;
    @Value("${wiremock-cloud}")
    private String url;

    public GatewayCartaoDeCredito(WebClient webClient) {
        this.webClient = webClient;
    }


    public GatewayCartaoDeCredito() {
        this.webClient = WebClient.builder()
                .baseUrl(url) // URL do WireMock
                .build();
    }

    /*

    block(): Usado para pegar a resposta de forma síncrona, mas não é recomendado em fluxos reativos.
    subscribe(): Usado para pegar a resposta de forma assíncrona, sem bloquear o fluxo.
    doOnSuccess(): Usado para executar uma ação quando o Mono for concluído com sucesso, mantendo o fluxo reativo.

    onErrorResume(): Usado para capturar exceções e fornecer um valor alternativo ou lógica de fallback.
    doOnError(): Usado para executar uma ação quando ocorre um erro, sem alterar o fluxo reativo.
    Essas abordagens permitem que você trate erros de forma flexível e reativa, sem bloquear o fluxo de execução.
     */
    public Mono<String> processaPagamento(Compra compra) {
        return webClient.post()
                .uri("/payment/process")
                .bodyValue(new GatewayPagamentoCartaoRequest(compra.getMetadadosCompra().getInfoCompraCartao()))
                .exchangeToMono(this::handleResponse);
    }

    private Mono<String> handleResponse(ClientResponse response) {
        if (response.statusCode().is4xxClientError()) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new RuntimeException("Erro 4xx: " + errorBody)));
        } else if (response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new RuntimeException("Erro 5xx: " + errorBody)));
        } else {
            return response.bodyToMono(String.class);
        }
    }
}
