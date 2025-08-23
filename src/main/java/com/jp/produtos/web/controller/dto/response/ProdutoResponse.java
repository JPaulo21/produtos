package com.jp.produtos.web.controller.dto.response;

import com.jp.produtos.domain.produto.Produto;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
        Integer quantidade
) {
    public static ProdutoResponse toModel(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}
