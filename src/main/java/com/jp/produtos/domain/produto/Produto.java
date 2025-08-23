package com.jp.produtos.domain.produto;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}
