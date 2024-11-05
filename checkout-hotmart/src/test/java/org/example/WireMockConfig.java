package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class WireMockConfig {

    @Bean
    @Scope("singleton")
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
        configureStub(wireMockServer); // Método para configurar stubs
        return wireMockServer;
    }

    private void configureStub(WireMockServer wireMockServer) {
        WireMock.configureFor("localhost", 8089);

        // Exemplo de stub para o gateway de pagamento
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/api/payment"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"status\":\"SUCCESS\",\"transactionId\":\"12345\"}")));
    }
}

