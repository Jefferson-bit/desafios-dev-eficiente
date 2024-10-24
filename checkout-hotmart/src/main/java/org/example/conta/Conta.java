package org.example.conta;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.example.produto.Produto;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "conta")
    private Set<Produto> produtos = new HashSet<>();

}
