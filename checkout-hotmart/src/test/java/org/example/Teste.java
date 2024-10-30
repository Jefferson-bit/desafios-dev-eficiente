package org.example;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Teste {

    @Test
    void instant() {
        String cadidate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(cadidate.charAt(random.nextInt(cadidate.length())));
        }

        System.out.println(sb.toString());
    }
}

