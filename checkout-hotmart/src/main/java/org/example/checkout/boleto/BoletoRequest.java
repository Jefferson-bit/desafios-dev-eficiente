package org.example.checkout.boleto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.checkout.CheckoutRequest;
import org.hibernate.validator.constraints.br.CPF;

@JsonTypeName(value = "cartaoDeCredito")
public class BoletoRequest extends CheckoutRequest {

    @CPF
    private String cpf;

    public BoletoRequest(String nome, String email, String confirmacaoEmail, String cpf) {
        super(nome, email, confirmacaoEmail);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
