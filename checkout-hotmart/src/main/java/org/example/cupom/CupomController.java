package org.example.cupom;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class CupomController {

    private final ProdutoRepository produtoRepository;

    public CupomController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/cupons/{codigoProduto}")
    @Transactional
    public ResponseEntity<?> cadastraCupom(@RequestBody @Valid CupomRequest request, @PathVariable("codigoProduto") UUID codigoProduto) {

        var produto = produtoRepository.findByCodigoGlobal(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo do produto nao encontrado"));

        var cupomEntity = request.toEntity(produto);
        return produto.adicionaCupom(cupomEntity,
                success -> ResponseEntity.ok("Cupom cadastrado com sucesso " + success.getCodigoDoCupom()),
                error -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error));
    }
}
