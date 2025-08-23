package com.jp.produtos.web.controller;

import com.jp.produtos.domain.produto.ProdutoService;
import com.jp.produtos.web.controller.docs.ProdutoDocs;
import com.jp.produtos.web.controller.dto.request.ProdutoRequest;
import com.jp.produtos.web.controller.dto.response.ProdutoResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController implements ProdutoDocs {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ProdutoRequest produtoRequest){
        var produto = produtoService.save(produtoRequest.toEntity());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> listAll(@RequestParam(required = false) String nome,
                                                         @Parameter(hidden = true) @PageableDefault Pageable pageable){
        Page<ProdutoResponse> produtoResponsePage = produtoService.listAll(nome, pageable)
                .map(ProdutoResponse::toModel);
        return ResponseEntity.ok(produtoResponsePage);
    }
}
