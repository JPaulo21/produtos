package com.jp.produtos.web.controller.docs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Endpoint para obter token de autenticação")
public interface AuthDocs {

    @Operation(summary = "Gerar token de autenticação", tags = {"Autenticação"})
    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso!",
            content = @Content(schema = @Schema(implementation = ObjectNode.class)))
    ResponseEntity<ObjectNode> token();
}
