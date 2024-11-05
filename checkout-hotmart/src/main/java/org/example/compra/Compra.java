package org.example.compra;

import org.example.conta.Conta;
import org.example.enums.MeioDePagamentoEnum;
import org.example.oferta.Oferta;

public class Compra {

    private MeioDePagamentoEnum meioDePagamento;
    private Conta conta;
    private Oferta oferta;

    public Compra(MeioDePagamentoEnum meioDePagamento, Conta conta, Oferta oferta) {
        this.meioDePagamento = meioDePagamento;
        this.conta = conta;
        this.oferta = oferta;
    }

    public Compra(Conta conta, Oferta oferta) {
        this.conta = conta;
        this.oferta = oferta;
    }

    public Conta getConta() {
        return conta;
    }

    public Oferta getOferta() {
        return oferta;
    }
}
