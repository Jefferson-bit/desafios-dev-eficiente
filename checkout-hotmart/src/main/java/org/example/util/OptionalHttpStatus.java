package org.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class OptionalHttpStatus {

    //todo procurar outra maneira de n passar o optional como argumento
    public static <R> R execute(Optional<R> repository, HttpStatus status, String errorMesage) {
        return repository.orElseThrow(() -> new ResponseStatusException(status, errorMesage));
    }
}
