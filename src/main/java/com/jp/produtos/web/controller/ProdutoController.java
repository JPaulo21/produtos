package com.jp.produtos.web.controller;

import com.jp.produtos.domain.produto.Produto;
import com.jp.produtos.domain.produto.ProdutoService;
import com.jp.produtos.web.controller.docs.ProdutoDocs;
import com.jp.produtos.web.controller.dto.request.ProdutoRequest;
import com.jp.produtos.web.controller.dto.response.ProdutoResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/produtos", produces = APPLICATION_JSON_VALUE)
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
                                                         @ParameterObject @PageableDefault(sort = "preco",direction = ASC) Pageable pageable){
        Page<ProdutoResponse> produtoResponsePage = produtoService.listAll(nome, pageable)
                .map(ProdutoResponse::toModel);
        return ResponseEntity.ok(produtoResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getById(@PathVariable("id") Long id) {
        Produto produto = produtoService.getById(id);
        return ResponseEntity.ok(ProdutoResponse.toModel(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
                                       @RequestBody @Valid ProdutoRequest produtoRequest) {
        produtoService.update(id, produtoRequest.toEntity());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
