package org.example.compra;

import jakarta.persistence.*;
import org.example.enums.StatusCompra;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class TransacaoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instant = LocalDateTime.now();
    @ManyToOne
    private Compra compra;
    @Enumerated(EnumType.STRING)
    private StatusCompra status;
    private String idTransacao;

    public TransacaoCompra(){}

    public TransacaoCompra(Compra compra, StatusCompra status) {
        this.compra = compra;
        this.status = status;
    }

    public TransacaoCompra(Compra compra, StatusCompra status, String idTransacao) {
        this.compra = compra;
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Optional<String> buscaIdTransacao(){
        return Optional.ofNullable(this.idTransacao);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public Compra getCompra() {
        return compra;
    }

    public StatusCompra getStatus() {
        return status;
    }
}
