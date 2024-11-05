package org.example.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public enum PagadorDeJurosEnum {

    CLIENTE(0) {
        @Override
        public List<BigDecimal> calculaJuros(BigDecimal preco, BigDecimal taxaDeJurosMensal, BigDecimal taxaDeComissao, int numeroDeParcelas) {
            List<BigDecimal> precoPorParcelas = new LinkedList<>();
            for (int vect = 1; vect <= numeroDeParcelas; vect++) {
                var i = taxaDeJurosMensal.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_EVEN);
                var fatorJurosComposto = BigDecimal.ONE.add(i).pow(numeroDeParcelas);
                var numerador = preco.multiply(fatorJurosComposto).multiply(i);
                var denominador = fatorJurosComposto.subtract(BigDecimal.ONE);
                precoPorParcelas.add(numerador.divide(denominador, 2, RoundingMode.HALF_EVEN));
            }
            return precoPorParcelas;
        }
    },
    VENDEDOR(1) {
        @Override
        public List<BigDecimal> calculaJuros(BigDecimal preco, BigDecimal taxaDeJuros, BigDecimal taxaDeComissao, int numerosDeParcelas) {
            List<BigDecimal> precoPorParcelas = new LinkedList<>();
            precoPorParcelas.add(preco.divide(BigDecimal.valueOf(numerosDeParcelas), RoundingMode.HALF_DOWN));
            var valorParcela = preco.divide(BigDecimal.valueOf(numerosDeParcelas), RoundingMode.HALF_UP);
            BigDecimal comissao = preco.multiply(taxaDeComissao).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal valorLiquido = preco.subtract(comissao);
            System.out.printf("O valor de cada parcela (sem juros) para o cliente é: R$ %.2f%n", valorParcela);
            System.out.printf("O valor líquido recebido pelo vendedor após a comissão é: R$ %.2f%n", valorLiquido);
            return precoPorParcelas;
        }
    };

    private final int code;

    PagadorDeJurosEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract List<BigDecimal> calculaJuros(BigDecimal preco,
                                                  BigDecimal taxaDeJuros,
                                                  BigDecimal taxaDeComissao,
                                                  int numerosDeParcelas);

}
