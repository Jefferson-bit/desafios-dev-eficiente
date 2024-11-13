package org.example.externalservice;


import org.example.compra.Compra;
import org.example.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GatewayCartaoDeCredito {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayCartaoDeCredito.class);

    private final WebClient webClient;
    private final EmailService emailService;

    public GatewayCartaoDeCredito(WebClient.Builder webClientBuilder, @Value("${wiremock-cloud}") String url, EmailService emailService) {
        this.emailService = emailService;
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    public Mono<String> processaPagamentoComCartao(Compra compra) {
        return webClient.post()
                .uri("/process/payment")
                .bodyValue(new GatewayPagamentoCartaoRequest(compra.getMetadadosCompra().getInfoCompraCartao()))
                .exchangeToMono(clientResponse -> handleResponse(clientResponse, compra))
                .doOnSuccess(success -> LOGGER.info("Pagamento efetuado com sucesso {} ", success))
                .doOnError(error -> LOGGER.error("Falha ao efetua pagamento", error));
    }

    private Mono<String> handleResponse(ClientResponse response, Compra compra) {
        if (response.statusCode().is4xxClientError()) {
            emailService.sendSimpleMessage(compra.getConta().getEmail(), "Pagamento", "Nao foi possivel efetuar o pagamento");
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new RuntimeException("Erro 4xx: " + errorBody)));
        } else if (response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new RuntimeException("Erro 4xx: " + errorBody)));
        } else {
            emailService.sendSimpleMessage(compra.getConta().getEmail(), "Pagamento", "Pagamento efetuado com sucesso");
            return response.bodyToMono(String.class);
        }
    }
}
