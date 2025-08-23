package com.jp.produtos.web.controller;

import com.jp.produtos.domain.produto.ProdutoService;
import com.jp.produtos.web.controller.docs.ProdutoDocs;
import com.jp.produtos.web.controller.dto.request.ProdutoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
