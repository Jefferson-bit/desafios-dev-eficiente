package org.example.configuracao;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ConfiguracaoRequest(
        @Positive
        BigDecimal taxaDeComissao,
        @Positive
        BigDecimal taxaDeJuros,
        @NotNull
        Boolean opcaoPadrao) {

   public Configuracao toEntity(){
        return new Configuracao(this.taxaDeComissao, this.taxaDeJuros, this.opcaoPadrao);
   }
}
