package org.example.oferta;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.enums.PagadorDeJurosEnum;
import org.example.produto.Produto;

import java.math.BigDecimal;

@Entity(name = "Oferta")
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive(message = "preco nao pode ser zero")
    @Column(nullable = false)
    private BigDecimal preco;
    @Min(value = 1)
    @Max(value = 12)
    @NotNull
    @Column(nullable = false)
    private Integer numerosDeParcelas;
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;
    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private PagadorDeJurosEnum pagadorDeJuros;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotNull
    @Column(nullable = false)
    private Boolean ativa = true;
    @Column(nullable = false)
    private Boolean principal = true;

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public @NotNull @Positive(message = "preco nao pode ser zero") BigDecimal getPreco() {
        return preco;
    }

    public @Min(value = 1) @Max(value = 12) @NotNull Integer getNumerosDeParcelas() {
        return numerosDeParcelas;
    }

    public Produto getProduto() {
        return produto;
    }

    public @NotNull PagadorDeJurosEnum getPagadorDeJuros() {
        return pagadorDeJuros;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public @NotNull Boolean getAtiva() {
        return ativa;
    }

    public Boolean getPrincipal() {
        return principal;
    }
}
