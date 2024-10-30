package org.example.cupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.*;
import org.example.produto.Produto;

import java.math.BigDecimal;
import java.time.Instant;

public record CupomRequest(
        @NotBlank
        String codigoDoCupom,
        @Future
        @JsonSerialize(using = InstantSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        @JsonDeserialize(using = InstantDeserializer.class)
        Instant vencimentoDoCupom,
        @DecimalMin(value = "1")
        @DecimalMax(value = "50")
        BigDecimal valorDoDesconto) {

    public Cupom toEntity(Produto produto) {
        return new Cupom(produto, this.codigoDoCupom, this.vencimentoDoCupom, this.valorDoDesconto);
    }
}
