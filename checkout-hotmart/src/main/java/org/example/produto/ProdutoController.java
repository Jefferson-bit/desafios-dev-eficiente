package org.example.produto;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.conta.ContaRepository;
import org.example.util.OptionalHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProdutoController {

    private final ContaRepository contaRepository;

    public ProdutoController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping("/produtos/{codigoConta}")
    @Transactional
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid ProdutoRequest request, @PathVariable UUID codigoConta) {

        //todo torna logica generico visto que ela se repete em demais fluxos
        var conta = OptionalHttpStatus.execute(contaRepository.findByCodigoGlobal(codigoConta),
                HttpStatus.NOT_FOUND, "Codigo da conta nao encontrado");

        return conta.adicionaProduto(request.toEntity(conta),
                success -> ResponseEntity.ok("Produto cadastrado com sucesso " + success.getCodigoGlobal()),
                error -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error));
    }
}
