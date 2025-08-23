package com.jp.produtos.web.controller.docs;

import com.jp.produtos.web.controller.dto.request.ProdutoRequest;
import com.jp.produtos.web.controller.dto.response.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public interface ProdutoDocs {

    @Operation(summary = "Salvar um novo produto", description = "Endpoint para salvar um novo produto no sistema")
    @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso",
            content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
    ResponseEntity<Void> save(ProdutoRequest produtoRequest);

    @Operation(summary = "Listar produtos", description = "Endpoint para listar produtos com paginação e filtro opcional por nome")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
    ResponseEntity<Page<ProdutoResponse>> listAll(String nome, Pageable pageable);
}
