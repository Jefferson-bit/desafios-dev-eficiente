package org.example.compra;

import org.example.enums.MeioDePagamentoEnum;
import org.example.oferta.Oferta;

import java.math.BigDecimal;

public class Compra {

    private MeioDePagamentoEnum meioDePagamento;
    private String email;
    private String codigoDoCupom;
    private Oferta oferta;

    public Compra(String email, Oferta oferta, MeioDePagamentoEnum meioDePagamento) {
        this.email = email;
        this.oferta = oferta;
        this.meioDePagamento = meioDePagamento;
    }

    public void processaCheckout(BigDecimal preco, Boolean principal) {

    }

    public void verificaSeExisteConta() {

    }

}
