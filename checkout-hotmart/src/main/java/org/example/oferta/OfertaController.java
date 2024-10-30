package org.example.oferta;


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
public class OfertaController {

    private final ProdutoRepository produtoRepository;

    public OfertaController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("ofertas/{codigoProduto}")
    @Transactional
    public ResponseEntity<?> cadastraOferta(@RequestBody @Valid OfertaRequest request, @PathVariable("codigoProduto") UUID codigoProduto) {
        var produto = produtoRepository.findByCodigoGlobal(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo do produto nao encontrado"));

        Oferta ofertaEntity = request.toEntity(produto);
        return produto.adicionaOferta(ofertaEntity,
                success -> {
                    produto.isOfertaPrincipal(ofertaEntity);
                    return ResponseEntity.ok("Produto cadastrado com sucesso " + success.getCodigoGlobal());
                },
                error -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error));
    }
}
