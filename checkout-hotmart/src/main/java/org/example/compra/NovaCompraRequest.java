package org.example.compra;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.checkout.cartaodecredito.CartaDeCreditoRequest;
import org.example.enums.MeioDePagamentoEnum;
import org.example.oferta.Oferta;

import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaDeCreditoRequest.class, name = "cartaoDeCredito")
})
public class NovaCompraRequest {

    @NotBlank
    @Email
    private String email;
    @JsonProperty("codigoDoCupom")
    private String codigoDoCupom;

    public NovaCompraRequest(String email, String codigoDoCupom) {
        this.email = email;
        this.codigoDoCupom = codigoDoCupom;
    }

    public Compra toEntity(Oferta oferta){
        return new Compra(this.email, oferta, MeioDePagamentoEnum.CARTAO_DE_CREDITO);
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> retornaCodigoDoCupom() {
        return Optional.ofNullable(this.codigoDoCupom);
    }
}
