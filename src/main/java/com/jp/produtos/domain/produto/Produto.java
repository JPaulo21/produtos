package com.jp.produtos.domain.produto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
}
