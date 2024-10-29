package org.example.oferta;

import org.example.enums.PagadorDeJurosEnum;

import java.math.BigDecimal;

public class OfertaRequest {


    private BigDecimal preco;
    private Integer numerosDeParcelas;
    private PagadorDeJurosEnum pagadorDeJuros;
    private String nome;
    private Boolean ativa;
    private Boolean principal;

    public OfertaRequest(BigDecimal preco, Integer numerosDeParcelas, PagadorDeJurosEnum pagadorDeJuros, String nome,
                         Boolean ativa, Boolean principal) {
        this.preco = preco;
        this.numerosDeParcelas = numerosDeParcelas;
        this.pagadorDeJuros = pagadorDeJuros;
        this.nome = nome;
        this.ativa = ativa;
        this.principal = principal;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumerosDeParcelas() {
        return numerosDeParcelas;
    }

    public PagadorDeJurosEnum getPagadorDeJuros() {
        return pagadorDeJuros;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public Boolean getPrincipal() {
        return principal;
    }
}
