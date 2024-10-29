package org.example.configuracao;


import jakarta.persistence.*;
import org.example.conta.Conta;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Configuracao")
@Table(name = "configuracao")
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal taxaDeComissao;
    @Column(nullable = false)
    private BigDecimal taxaDeJuros;
    @OneToMany(mappedBy = "configuracao", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Conta> contas = new HashSet<>();
    @Column(nullable = false)
    private Boolean opcaoPadrao;

    @Deprecated
    public Configuracao(){}

    public Configuracao(BigDecimal taxaDeComissao, BigDecimal taxaDeJuros, Boolean opcaoPadrao) {
        this.taxaDeComissao = taxaDeComissao;
        this.taxaDeJuros = taxaDeJuros;
        this.opcaoPadrao = opcaoPadrao;
    }

    public Long getId() {
        return id;
    }

    public Boolean getOpcaoPadrao() {
        return opcaoPadrao;
    }
}
