package com.farmacia.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.farmacia.farmacia.dto.ProdutoDTO;
import com.farmacia.farmacia.entities.Produto;
import com.farmacia.farmacia.service.ProdutoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.createProduto(produtoDTO);
    }

    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.getAllProdutos();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        Optional<ProdutoDTO> updateProduto = produtoService.updateProduto(id, produtoDTO);
        return updateProduto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id){
        boolean itemDeletado = produtoService.deleteProduto(id);
        if (itemDeletado){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProdutoDTO>> searchProdutos(@RequestParam String nome) {
        List<ProdutoDTO> produtos = produtoService.findByNomeContaining(nome);
        return ResponseEntity.ok(produtos);
    }
    

}
