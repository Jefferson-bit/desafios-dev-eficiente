package org.example.produto;

import jakarta.validation.constraints.NotBlank;
import org.example.conta.Conta;
import org.example.customannotation.ValidaNomeProduto;
import org.example.oferta.OfertaRequest;

import java.util.Set;

public record ProdutoRequest(
        @NotBlank
//        @ValidaNomeProduto(tabela = Produto.class, campo = "nome")
        String nome,
        @NotBlank
        String descricao) {

    public Produto toEntity(Conta conta) {
        return new Produto(this.nome, this.descricao, conta);
    }
}
