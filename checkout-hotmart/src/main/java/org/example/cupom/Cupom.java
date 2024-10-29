package org.example.cupom;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.produto.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Cupom")
@Table(name = "cupom")
public class Cupom implements Serializable {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
    private String codigoDoCupom;
    @Future
    private Instant vencimentoDoCupom;
    @Min(value = 1)
    @Max(value = 50)
    private BigDecimal valorDoDesconto;
}
