
INSERT INTO configuracao(taxa_de_comissao,taxa_de_juros,opcao_padrao) VALUES (12,2,true);
INSERT INTO conta(email,codigo_global,configuracao_id) VALUES ('example@gmail.com','5947687c-51fe-479e-9bbf-20cd00afd6a7',1);
INSERT INTO produto(nome,descricao,codigo_global,conta_id) VALUES ('Dev Eficiente','Curso para evolucao','46437ab2-9b3a-4996-9ddb-9e9692303f09',1);
INSERT INTO oferta(preco,numeros_de_parcelas,codigo_global, produto_id, pagador_de_juros,nome,ativa,principal) VALUES (123.0,5,'23ff2c94-c12b-4a7e-9b0f-5c950f9896f0',1,0,'Black Friday', true, true);
INSERT INTO cupom(produto_id,codigo_do_cupom,vencimento_do_cupom,valor_do_desconto) VALUES (1,'BMW','2024-10-30T23:00:00.000Z',12.0);
