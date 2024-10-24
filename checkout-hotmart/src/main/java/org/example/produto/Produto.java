package org.example.produto;

import jakarta.persistence.*;
import org.example.conta.Conta;
import org.example.cupom.Cupom;
import org.example.oferta.Oferta;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
    @OneToMany(mappedBy = "produto")
    private Set<Oferta> ofertas = new HashSet<>();
    private Cupom cupom;
}
