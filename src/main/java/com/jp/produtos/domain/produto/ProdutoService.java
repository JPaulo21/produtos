package com.jp.produtos.domain.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public Page<Produto> listAll(String nome, Pageable pageable) {
        Specification<Produto> spec = Specification.allOf(hasNome(nome));

        return produtoRepository.findAll(spec ,pageable);
    }

    @Transactional(readOnly = true)
    public Produto getById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public void update(Long id, Produto updateProduto) {
        Produto produto = this.getById(id);

        produto.setNome(updateProduto.getNome() != null ? updateProduto.getNome() : produto.getNome());
        produto.setDescricao(updateProduto.getDescricao() != null ? updateProduto.getDescricao() : produto.getDescricao());
        produto.setPreco(updateProduto.getPreco() != null ? updateProduto.getPreco() : produto.getPreco());
        produto.setQuantidadeEstoque(updateProduto.getQuantidadeEstoque() != null ?
                updateProduto.getQuantidadeEstoque() : produto.getQuantidadeEstoque());
        produto.setDataCriacao(updateProduto.getDataCriacao() != null ?
                updateProduto.getDataCriacao() : produto.getDataCriacao());

        produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = this.getById(id);
        produtoRepository.delete(produto);
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
