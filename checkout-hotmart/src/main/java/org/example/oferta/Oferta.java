package org.example.oferta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.enums.TipoPagadorDeJuros;
import org.example.produto.Produto;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Oferta")
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;
    @Column(nullable = false)
    private Integer numerosDeParcelas;
    private UUID codigoGlobal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private TipoPagadorDeJuros pagadorDeJuros;
    @Column(nullable = false)
    private String nome;
    private Boolean ativa;
    private Boolean principal;

    @Deprecated
    Oferta() {
    }

    public Oferta(BigDecimal preco, Integer numerosDeParcelas, Produto produto,
                  TipoPagadorDeJuros pagadorDeJuros, String nome) {
        this.preco = preco;
        this.numerosDeParcelas = numerosDeParcelas;
        this.produto = produto;
        this.pagadorDeJuros = pagadorDeJuros;
        this.nome = nome;
        this.ativa = true;
        this.principal = false;
        this.codigoGlobal = UUID.randomUUID();
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumerosDeParcelas() {
        return numerosDeParcelas;
    }

    public UUID getCodigoGlobal() {
        return codigoGlobal;
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoPagadorDeJuros getPagadorDeJuros() {
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

    public void defineOfertaComoPrincipal() {
        this.principal = true;
    }
}
