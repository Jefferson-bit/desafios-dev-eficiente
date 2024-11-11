package org.example.compra;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class InfoCompraCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroDoCartao;
    private String nomeDoTitular;
    private BigDecimal valorDaParcela;
    private Integer anoDoVencimento;
    private Integer mesDoVencimento;
    private Integer numerosDeParcelas;

    public InfoCompraCartao() {
    }

    public InfoCompraCartao(String numeroDoCartao, String nomeDoTitular,BigDecimal valorDaParcela,
                            Integer anoDoVencimento, Integer mesDoVencimento, Integer numerosDeParcelas) {
        this.numeroDoCartao = numeroDoCartao;
        this.nomeDoTitular = nomeDoTitular;
        this.valorDaParcela = valorDaParcela;
        this.anoDoVencimento = anoDoVencimento;
        this.mesDoVencimento = mesDoVencimento;
        this.numerosDeParcelas = numerosDeParcelas;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public BigDecimal getValorDaParcela() {
        return valorDaParcela;
    }

    public Integer getAnoDoVencimento() {
        return anoDoVencimento;
    }

    public Integer getMesDoVencimento() {
        return mesDoVencimento;
    }

    public Integer getNumerosDeParcelas() {
        return numerosDeParcelas;
    }
}
