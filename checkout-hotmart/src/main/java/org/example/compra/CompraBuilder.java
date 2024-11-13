package org.example.compra;

import org.example.checkout.boleto.BoletoRequest;
import org.example.checkout.cartaodecredito.CartaoDeCreditoRequest;
import org.example.conta.Conta;
import org.example.cupom.Cupom;
import org.example.oferta.Oferta;

import java.util.concurrent.Flow;
import java.util.function.Function;

public final class CompraBuilder {
    private Conta conta;
    private Oferta oferta;
    private Cupom cupom;

    public CompraBuilder comConta(Conta conta) {
        this.conta = conta;
        return this;
    }

    public CompraBuilder comOferta(Oferta oferta) {
        this.oferta = oferta;
        return this;
    }

    public CompraBuilder comCupom(Cupom cupom) {
        this.cupom = cupom;
        return this;
    }

    public Compra comCartao(CartaoDeCreditoRequest request) {
        Function<Compra, MetadadosCompra> criaMetadados = compraFunction ->{
            return new MetadadosCompra(compraFunction,
                    new InfoCompraCartao(
                            request.getNumeroDoCartao(),
                            request.getNomeDoTitular(),
                            compraFunction.retornaCalculoDoJuros(),
                            request.getAnoDoVencimento(),
                            request.getMesDoVencimento(),
                            request.getNumerosDeParcelas()));

        };
        if (cupom == null) {
            return new Compra(conta, oferta, criaMetadados);
        }
        return new Compra(conta, oferta, cupom, criaMetadados);
    }

    public Compra comBoleto(BoletoRequest infoCompraBoleto) {
        Function<Compra, MetadadosCompra> criaMetadados = compraFunction -> new MetadadosCompra(compraFunction,
                new InfoCompraBoleto());
        if (cupom != null) {
            return new Compra(conta, oferta, cupom, criaMetadados);
        }
        return new Compra(conta, oferta, criaMetadados);
    }
}
