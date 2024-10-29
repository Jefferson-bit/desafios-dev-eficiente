package org.example.produto;


import jakarta.validation.Valid;
import org.example.conta.ContaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class ProdutoController {


    private final ContaRepository contaRepository;
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ContaRepository contaRepository, ProdutoRepository produtoRepository) {
        this.contaRepository = contaRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/produtos/{codigoConta}")
    public void cadastraProduto(@RequestBody @Valid ProdutoRequest request, @PathVariable UUID codigoConta) {

        var conta = contaRepository.findByCodigoGlobal(codigoConta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo da conta nao encontrado"));

        conta.getProdutos().forEach(produtoStream -> {
            if (produtoStream.getNome().equals(request.nome()))
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Nome do produto já existe para essa conta");
        });

        var produtoEntity = request.toEntity(conta);
        produtoRepository.save(produtoEntity);
    }
}
