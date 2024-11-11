package org.example.compra;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class InfoCompraBoleto {

    @Id
    private Long id;
    private String cpf;
    private String codigoBoleto;
    private BigDecimal valor;
    private LocalDate dataDaExpiracao;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private MetadadosCompra metadadosCompra;

    public InfoCompraBoleto(){}

    public InfoCompraBoleto(String cpf, String codigoBoleto, BigDecimal valor, LocalDate dataDaExpiracao) {
        this.cpf = cpf;
        this.codigoBoleto = codigoBoleto;
        this.valor = valor;
        this.dataDaExpiracao = dataDaExpiracao;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataDaExpiracao() {
        return dataDaExpiracao;
    }
}
