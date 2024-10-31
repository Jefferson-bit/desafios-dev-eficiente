package org.example.checkout.cartaodecredito;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.conta.ContaRepository;
import org.example.oferta.Oferta;
import org.example.oferta.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class CartaoDeCreditoController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private OfertaRepository ofertaRepository;

    @PostMapping("/cartaodecreditos/{codigoOferta}")
    @Transactional
    public void test(@RequestBody @Valid CheckoutCartaDeCreditoRequest request,
                     @PathVariable("codigoOferta")  UUID codigoOferta) {

        Optional<Oferta> byCodigoGlobal = ofertaRepository.findByCodigoGlobal(codigoOferta);


    }


}
