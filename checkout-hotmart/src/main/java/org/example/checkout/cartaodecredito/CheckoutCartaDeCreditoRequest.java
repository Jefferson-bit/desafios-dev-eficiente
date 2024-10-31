package org.example.checkout.cartaodecredito;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.conta.Conta;
import org.example.util.ValorUnico;

public record CheckoutCartaDeCreditoRequest(

        @NotBlank
        @Email
        @ValorUnico(tabela = Conta.class, campo = "email", message = "email existente")
        String email,
        String codigoDoCupom) {
}
