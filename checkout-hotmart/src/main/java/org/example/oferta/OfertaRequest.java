package org.example.oferta;

import jakarta.validation.constraints.*;
import org.example.enums.TipoPagadorDeJuros;
import org.example.produto.Produto;

import java.math.BigDecimal;

public record OfertaRequest(
        @Positive
        @NotNull
        BigDecimal preco,
        @Min(value = 1)
        @Max(value = 12)
        @NotNull
        Integer numerosDeParcelas,
        @NotNull
        TipoPagadorDeJuros pagadorDeJuros,
        @NotBlank
        String nome) {

    public Oferta toEntity(Produto produto) {
        return new Oferta(this.preco, this.numerosDeParcelas, produto, this.pagadorDeJuros, this.nome);
    }
}
