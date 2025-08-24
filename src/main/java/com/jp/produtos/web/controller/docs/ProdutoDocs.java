package com.jp.produtos.web.controller.docs;

import com.jp.produtos.web.controller.dto.request.ProdutoRequest;
import com.jp.produtos.web.controller.dto.response.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Produtos", description = "Gerenciamento de produtos - TOKEN OBRIGATÓRIO")
public interface ProdutoDocs {

    @Operation(summary = "Salvar um novo produto", description = "Endpoint para salvar um novo produto no sistema")
    @ApiResponse(responseCode = "201", description = "Header Location com a localização do novo produto",
            content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
    ResponseEntity<Void> save(ProdutoRequest produtoRequest);

    @Operation(summary = "Listar produtos", description = "Endpoint para listar produtos com paginação e filtro opcional por nome")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
    ResponseEntity<Page<ProdutoResponse>> listAll(String nome, Pageable pageable);

    @Operation(summary = "Obter produto por ID", description = "Endpoint para obter os detalhes de um produto específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Detalhes do produto",
            content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
    ResponseEntity<ProdutoResponse> getById(Long id);

    @Operation(summary = "Atualizar produto", description = "Endpoint para atualizar os detalhes de um produto existente")
    @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso")
    ResponseEntity<Void> update(Long id, ProdutoRequest produtoRequest);

    @Operation(summary = "Deletar produto", description = "Endpoint para deletar um produto existente pelo seu ID")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
