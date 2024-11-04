package org.example.checkout.cartaodecredito;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.checkout.CheckoutRequest;

@JsonTypeName(value = "cartaoDeCredito")
public class CartaoDeCreditoRequest extends CheckoutRequest {

    private Integer numeroDoCartao;
    private String nomeDoTitular;
    private Integer anoDoVencimento;
    private Integer mesDoVencimento;
    private Integer numerosDeParcelas;

    public CartaoDeCreditoRequest(String email, String codigoDoCupom, Integer numeroDoCartao,
                                  String nomeDoTitular, Integer anoDoVencimento,
                                  Integer mesDoVencimento, Integer numerosDeParcelas) {
        super(email, codigoDoCupom);
        this.numeroDoCartao = numeroDoCartao;
        this.nomeDoTitular = nomeDoTitular;
        this.anoDoVencimento = anoDoVencimento;
        this.mesDoVencimento = mesDoVencimento;
        this.numerosDeParcelas = numerosDeParcelas;
    }

    public Integer getNumeroDoCartao() {
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
