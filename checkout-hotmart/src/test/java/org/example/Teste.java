package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Teste {

    @Test
    void instant() {
        BigDecimal preco = new BigDecimal(5000.00);
        BigDecimal desconto = new BigDecimal(50.0);

        BigDecimal divide = (preco.multiply(desconto)).divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR);

        Assertions.assertEquals(61.50, divide.doubleValue());
    }
}

