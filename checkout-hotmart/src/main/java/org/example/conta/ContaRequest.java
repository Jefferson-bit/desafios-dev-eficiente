package org.example.conta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.configuracao.Configuracao;
import org.example.util.ValorUnico;

public record ContaRequest(
        @Email
        @NotBlank
        @ValorUnico(tabela = Conta.class, campo = "email", message = "email existente")
        String email) {

    public Conta toEntity(Configuracao config) {
        return new Conta(this.email, config);
    }
}
