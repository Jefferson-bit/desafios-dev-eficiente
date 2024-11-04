package org.example.conta;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.service.RegistraNovaContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ContaController {

    private final RegistraNovaContaService registraNovaContaService;

    public ContaController(RegistraNovaContaService registraNovaContaService) {
        this.registraNovaContaService = registraNovaContaService;
    }

    @PostMapping("/contas")
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid ContaRequest request) {
        var conta = registraNovaContaService.cadastraNovaConta(request.email());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta.getId())
                .toUri();
        return ResponseEntity.created(location).body(conta.getCodigoGlobal());
    }
}
