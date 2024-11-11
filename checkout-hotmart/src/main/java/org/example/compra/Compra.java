package org.example.compra;

import jakarta.persistence.*;
import org.example.conta.Conta;
import org.example.cupom.Cupom;
import org.example.enums.StatusCompra;
import org.example.enums.TipoPagadorDeJuros;
import org.example.oferta.Oferta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Conta conta;
    @ManyToOne
    private Oferta oferta;
    @ManyToOne
    private Cupom cupom;
    private BigDecimal precoMomento;
    private String codigoDaOferta;
    private BigDecimal taxaDejuros;
    private TipoPagadorDeJuros quemPagaOJuros;
    @OneToOne(mappedBy = "compra", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY,optional = false)
    private MetadadosCompra metadadosCompra;
    @OneToMany(mappedBy = "compra", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<TransacaoCompra> transacoes = new ArrayList<>();
    private BigDecimal precoFinal;

    public Compra(Conta conta, Oferta oferta, Function<Compra, MetadadosCompra> applyMetadados) {
        this(conta, oferta, null, applyMetadados);
    }

    public Compra(Conta conta, Oferta oferta, Cupom cupom, Function<Compra, MetadadosCompra> applyMetadados) {
        this.conta = conta;
        this.oferta = oferta;
        this.cupom = cupom;
        this.transacoes.add(new TransacaoCompra(this, StatusCompra.INICIADA));
        this.precoMomento = oferta.getPreco();
        this.codigoDaOferta = oferta.getCodigoGlobal().toString();
        this.quemPagaOJuros = oferta.getPagadorDeJuros();
        this.taxaDejuros = conta.getConfiguracao().getTaxaDeJuros();
        this.precoFinal = Optional.ofNullable(this.cupom).map(obj -> obj.aplicaDesconto(precoMomento)).orElse(precoMomento);
        this.metadadosCompra = applyMetadados.apply(this);
    }

    public Oferta getOferta() {
        return oferta;
    }

    public MetadadosCompra getMetadadosCompra() {
        return metadadosCompra;
    }

    public BigDecimal retornaCalculoDoJuros(){
        return this.quemPagaOJuros.getCalculoJuros().calcularJuros(this.oferta, this.conta.getConfiguracao());
    }
}
