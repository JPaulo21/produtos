package com.jp.produtos.web.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jp.produtos.domain.produto.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Schema(description = "Representa a requisição para criação de um novo produto")
public record ProdutoRequest(
        @Schema(description = "Nome do produto", example = "Notebook Dell")
        @NotBlank(message = "Campo nome não foi informado.")
        String nome,
        @Schema(description = "Descrição do produto", example = "Notebook Dell Inspiron 15 3000")
        String descricao,
        @Schema(description = "Preço do produto", example = "3500.00")
        @Positive(message = "O preço deve ser maior que zero")
        Double preco,
        @Schema(description = "Quantidade em estoque do produto", example = "10")
        @Positive(message = "A quantidade deve ser um número inteiro positivo.")
        Integer quantidadeEstoque,

        @Schema(description = "Data de criação do produto", example = "2025-06-23T12:00:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataCriacao
) {

    public Produto toEntity() {
        return Produto.builder()
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .quantidadeEstoque(quantidadeEstoque)
                .dataCriacao(dataCriacao)
                .build();
    }
}
