package org.example.conta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.configuracao.Configuracao;

public record ContaRequest(
        @Email
        @NotBlank
        String email) {

    public Conta toEntity(Configuracao config) {
        return new Conta(this.email, config);
    }
}
