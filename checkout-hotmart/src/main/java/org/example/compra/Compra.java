package org.example.compra;

import org.example.conta.Conta;
import org.example.enums.MeioDePagamentoEnum;
import org.example.oferta.Oferta;

import java.math.BigDecimal;

public class Compra {

    private MeioDePagamentoEnum meioDePagamento;
    private Conta conta;
    private Oferta oferta;


    public Compra(MeioDePagamentoEnum meioDePagamento, Conta conta, Oferta oferta) {
        this.meioDePagamento = meioDePagamento;
        this.conta = conta;
        this.oferta = oferta;
    }

    public void processaCheckout(BigDecimal preco, Boolean principal) {

    }

    public void verificaSeExisteConta() {

    }

}
