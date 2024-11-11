package org.example.compra;


import jakarta.persistence.*;

import java.util.function.Function;

@Entity
public class MetadadosCompra {

    @Id
    private Long Id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    Compra compra;
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private InfoCompraCartao infoCompraCartao;
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private InfoCompraBoleto infoCompraBoleto;

    public MetadadosCompra() {
    }

    public MetadadosCompra(Compra compra, InfoCompraCartao infoCompraCartao) {
        this.compra = compra;
        this.infoCompraCartao = infoCompraCartao;
    }

    public MetadadosCompra(Compra compra, InfoCompraBoleto infoCompraBoleto) {
        this.compra = compra;
        this.infoCompraBoleto = infoCompraBoleto;
    }

    public Compra getCompra() {
        return compra;
    }

    public InfoCompraCartao getInfoCompraCartao() {
        return infoCompraCartao;
    }

    public InfoCompraBoleto getInfoCompraBoleto() {
        return infoCompraBoleto;
    }
}
