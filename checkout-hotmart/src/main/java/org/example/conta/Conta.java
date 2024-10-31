package org.example.conta;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.example.configuracao.Configuracao;
import org.example.produto.Produto;

import java.util.*;
import java.util.function.Function;

@Entity(name = "Conta")
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private UUID codigoGlobal;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Produto> produtos = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuracao_id")
    private Configuracao configuracao;

    @Deprecated
    Conta() {}

    public Conta(String email, Configuracao configuracao) {
        this.email = email;
        this.configuracao = configuracao;
        this.codigoGlobal = UUID.randomUUID();
    }

    public <T> T adicionaProduto(Produto produto,
                                 Function<Produto, T> onSuccess,
                                 Function<String, T> onErro) {
        for (Produto existente : this.produtos) {
            if (existente.getNome().equals(produto.getNome())) {
                return onErro.apply("Já existe um produto cadastrado com esse nome");
            }
        }
        this.produtos.add(produto);
        return onSuccess.apply(produto);
    }

    public Long getId() {
        return id;
    }

    public @Email String getEmail() {
        return email;
    }

    public UUID getCodigoGlobal() {
        return codigoGlobal;
    }

    public Set<Produto> getProdutos() {
        return Collections.unmodifiableSet(this.produtos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id) && Objects.equals(email, conta.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
