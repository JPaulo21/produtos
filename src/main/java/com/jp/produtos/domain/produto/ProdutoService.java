package com.jp.produtos.domain.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public Page<Produto> listAll(String nome, Pageable pageable) {
        Specification<Produto> spec = Specification.allOf(hasNome(nome));

        return produtoRepository.findAll(spec ,pageable);
    }

    public Produto getById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // Ideal separar em uma classe com pattern Specification caso aumente o número de paramêtros
    public static Specification<Produto> hasNome(String nome) {
        return (root, query, cb) -> {
            if (nome != null && !nome.isEmpty())
                return cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
            return cb.conjunction();
        };
    }
}
