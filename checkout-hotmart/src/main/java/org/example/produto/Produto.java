package org.example.produto;

import jakarta.persistence.*;
import org.example.conta.Conta;
import org.example.cupom.Cupom;
import org.example.oferta.Oferta;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@Entity(name = "Produto")
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    private UUID codigoGlobal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private Conta conta;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Oferta> ofertas = new HashSet<>();
    private Cupom cupom;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, String descricao, Conta conta) {
        this.conta = conta;
        this.descricao = descricao;
        this.nome = nome;
        this.codigoGlobal = UUID.randomUUID();
    }

    public <T> T adicionaOferta(Oferta oferta,
                                Function<Oferta, T> onSuccess,
                                Function<String, T> onError) {
        for (Oferta existente : this.ofertas) {
            if (existente.getNome().equals(oferta.getNome())) {
                return onError.apply("Já existe uma oferta cadastrada com esse nome");
            }
        }
        this.ofertas.add(oferta);
        return onSuccess.apply(oferta);
    }

    public boolean isOfertaPrincipal(Oferta oferta) {
        var isNaoExisteOfertaPrincipal = this.ofertas.stream().noneMatch(Oferta::getPrincipal);
        if (isNaoExisteOfertaPrincipal) {
            oferta.defineOfertaComoPrincipal();
        }
        return isNaoExisteOfertaPrincipal;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Conta getConta() {
        return conta;
    }

    public Set<Oferta> getOfertas() {
        return ofertas;
    }

    public UUID getCodigoGlobal() {
        return codigoGlobal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
