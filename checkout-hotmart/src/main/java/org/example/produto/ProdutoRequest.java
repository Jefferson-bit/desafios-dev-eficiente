package org.example.produto;

import jakarta.validation.constraints.NotBlank;
import org.example.conta.Conta;

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
