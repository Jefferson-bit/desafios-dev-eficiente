package org.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class OptionalHttpStatus {

    public static <R> R execute(R repository, HttpStatus status, String errorMesage) {
        return Optional.ofNullable(repository).orElseThrow(() -> new ResponseStatusException(status, errorMesage));
    }
}
