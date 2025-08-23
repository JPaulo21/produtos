package com.jp.produtos.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jp.produtos.config.security.JWT.TokenService;
import com.jp.produtos.web.controller.docs.AuthDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<ObjectNode> token() {
        ObjectNode json = new ObjectMapper().createObjectNode();
        json.put("token", tokenService.generateToken());
        return ResponseEntity.ok(json);
    }
}
