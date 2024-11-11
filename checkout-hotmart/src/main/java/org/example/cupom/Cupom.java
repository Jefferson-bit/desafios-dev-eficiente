package org.example.cupom;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.exception.CupomVencidoException;
import org.example.produto.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.random.RandomGenerator;

@Entity(name = "Cupom")
@Table(name = "cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(nullable = false)
    private String codigoDoCupom;
    private Instant vencimentoDoCupom;
    private BigDecimal valorDoDesconto;

    @Deprecated
    Cupom() {
    }

    public Cupom(Produto produto, String codigoDoCupom, Instant vencimentoDoCupom, BigDecimal valorDoDesconto) {
        this.produto = produto;
        this.codigoDoCupom = codigoDoCupom;
        this.vencimentoDoCupom = vencimentoDoCupom;
        this.valorDoDesconto = valorDoDesconto;
    }

    public BigDecimal aplicaDesconto(BigDecimal preco) {
        return (preco.multiply(this.valorDoDesconto)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
    }

    public String getCodigoDoCupom() {
        return codigoDoCupom;
    }
}
