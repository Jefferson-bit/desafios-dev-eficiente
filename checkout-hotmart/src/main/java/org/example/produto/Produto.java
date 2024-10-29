package org.example.produto;

import jakarta.persistence.*;
import org.example.conta.Conta;
import org.example.cupom.Cupom;
import org.example.oferta.Oferta;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Oferta> ofertas;
    private Cupom cupom;

    @Deprecated
    public Produto(){}

    public Produto(String nome, String descricao, Conta conta) {
        this.conta = conta;
        this.descricao = descricao;
        this.nome = nome;
        this.codigoGlobal = UUID.randomUUID();
    }

    public void addOferta(Oferta oferta){
        this.ofertas.add(oferta);
        oferta.setProduto(this);
    }

    public void removeOferta(Oferta oferta){
        this.ofertas.remove(oferta);
        oferta.setProduto(null);
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

    public Cupom getCupom() {
        return cupom;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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
