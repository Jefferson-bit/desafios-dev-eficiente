package org.example.checkout;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.checkout.cartaodecredito.CartaoDeCreditoRequest;
import org.example.compra.Compra;
import org.example.conta.Conta;
import org.example.enums.MeioDePagamentoEnum;
import org.example.oferta.Oferta;

import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoDeCreditoRequest.class, name = "cartaoDeCredito")
})
public class CheckoutRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Email
    private String confirmacaoEmail;
    @JsonProperty("codigoDoCupom")
    private String codigoDoCupom;

    public CheckoutRequest(String email, String codigoDoCupom) {
        this.email = email;
        this.codigoDoCupom = codigoDoCupom;
    }

    public Compra toEntity(Oferta oferta, Conta conta) {
        return new Compra(MeioDePagamentoEnum.CARTAO_DE_CREDITO, conta, oferta);
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getCodigoDoCupom() {
        return Optional.ofNullable(this.codigoDoCupom);
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public @NotBlank @Email String getConfirmacaoEmail() {
        return confirmacaoEmail;
    }

}
