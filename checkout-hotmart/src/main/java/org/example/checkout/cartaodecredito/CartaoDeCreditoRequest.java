package org.example.checkout.cartaodecredito;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.checkout.CheckoutRequest;
import org.hibernate.validator.constraints.CreditCardNumber;

@JsonTypeName(value = "cartaoDeCredito")
public class CartaoDeCreditoRequest extends CheckoutRequest {

    @NotNull
    @CreditCardNumber(message = "cartao de credito invalido")
    private String numeroDoCartao;
    @NotBlank
    private String nomeDoTitular;
    @NotNull
    private Integer anoDoVencimento;
    @NotNull
    private Integer mesDoVencimento;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer numerosDeParcelas;

    public CartaoDeCreditoRequest(String nome, String email, String confirmacaoEmail, String numeroDoCartao,
                                  String nomeDoTitular, Integer anoDoVencimento,
                                  Integer mesDoVencimento, Integer numerosDeParcelas) {
        super(nome, email, confirmacaoEmail);
        this.numeroDoCartao = numeroDoCartao;
        this.nomeDoTitular = nomeDoTitular;
        this.anoDoVencimento = anoDoVencimento;
        this.mesDoVencimento = mesDoVencimento;
        this.numerosDeParcelas = numerosDeParcelas;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public Integer getAnoDoVencimento() {
        return anoDoVencimento;
    }

    public Integer getMesDoVencimento() {
        return mesDoVencimento;
    }

    public Integer getNumerosDeParcelas() {
        return numerosDeParcelas;
    }
}
