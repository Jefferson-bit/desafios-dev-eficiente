package org.example.enums;

import org.example.checkout.cartaodecredito.CalculoJuros;
import org.example.checkout.cartaodecredito.JurosParaCliente;
import org.example.checkout.cartaodecredito.JurosParaVendedor;

public enum TipoPagadorDeJuros {

    CLIENTE(0) {
        @Override
        public CalculoJuros getCalculoJuros() {
            return new JurosParaCliente();
        }
    },
    VENDEDOR(1) {
        @Override
        public CalculoJuros getCalculoJuros() {
            return new JurosParaVendedor();
        }
    };

    private final int code;

    TipoPagadorDeJuros(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract CalculoJuros getCalculoJuros();
}
