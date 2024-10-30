package org.example.cupom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantSerializer extends JsonSerializer<Instant> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public void serialize(Instant instant, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Converte o Instant para uma string ISO 8601 em UTC
        gen.writeString(formatter.format(instant));
    }
}

