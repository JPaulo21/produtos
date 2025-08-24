package com.jp.produtos.config.security;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        ObjectNode json = JsonMapper.builder().build().createObjectNode();
        json.put("status", 401);
        json.put("error", "Unauthorized");
        json.put("path", request.getRequestURI());
        json.put("message", "Necessita de token válido para acessar o recurso");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(new JsonMapper().writeValueAsString(json));
    }
}
