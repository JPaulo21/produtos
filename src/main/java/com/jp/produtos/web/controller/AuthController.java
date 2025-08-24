package com.jp.produtos.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jp.produtos.config.security.JWT.Token;
import com.jp.produtos.config.security.JWT.TokenService;
import com.jp.produtos.web.controller.docs.AuthDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/token", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<Token> token() {
        Token token = new Token(tokenService.generateToken());
        return ResponseEntity.ok(token);
    }
}
