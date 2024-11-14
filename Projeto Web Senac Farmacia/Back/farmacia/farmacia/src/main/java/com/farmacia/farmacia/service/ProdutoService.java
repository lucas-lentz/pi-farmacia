package com.farmacia.farmacia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.farmacia.farmacia.dto.CategoriaDTO;
import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.dto.MarcaDTO;
import com.farmacia.farmacia.dto.ProdutoDTO;
import com.farmacia.farmacia.entities.Categoria;
import com.farmacia.farmacia.entities.Marca;
import com.farmacia.farmacia.entities.Produto;
import com.farmacia.farmacia.repository.CategoriaRepository;
import com.farmacia.farmacia.repository.MarcaRepository;
import com.farmacia.farmacia.repository.ProdutoRepository;
import com.farmacia.farmacia.util.CategoriaMapper;
import com.farmacia.farmacia.util.DepartamentoMapper;
import com.farmacia.farmacia.util.MarcaMapper;
import com.farmacia.farmacia.util.ProdutoMapper;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // CREATE
    public ResponseEntity<ProdutoDTO> createProduto(ProdutoDTO produtoDTO) {
        // Busca a marca no repository como entidade
        Marca marca = marcaRepository.findById(produtoDTO.getMarcaDTO().getId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        // Converte a entidade marca para DTO
        MarcaDTO marcaDTO = MarcaMapper.toDtoCreate(marca);

        // Busca a categoria no repository como entidade
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaDTO().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // Mapeia também o departamento da categoria
        DepartamentoDTO departamentoDTO = DepartamentoMapper.toDtoCreate(categoria.getDepartamento());
        CategoriaDTO categoriaDTO = CategoriaMapper.toDTOCreate(categoria, departamentoDTO);

        // Converte produtoDTO para entidade, incluindo marca e categoria
        Produto produto = ProdutoMapper.toEntityCreate(produtoDTO, marca, categoria);

        // Salva e converte para DTO
        ProdutoDTO produtoDTOSalvo = ProdutoMapper.toDTOCreate(produtoRepository.save(produto), marcaDTO, categoriaDTO);

        return ResponseEntity.ok(produtoDTOSalvo);
    }

    // READ
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    
    // PUT
    public Optional<ProdutoDTO> updateProduto(Long id, ProdutoDTO produtoDTO){
        return produtoRepository.findById(id).map(produto ->{
            Marca marca = marcaRepository.findById(produtoDTO.getMarcaDTO().getId()).orElseThrow(() -> new RuntimeException("Marca não encontrada!"));
            Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaDTO().getId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
            produto = ProdutoMapper.toEntityUpdate(produto, produtoDTO,marca,categoria);
            ProdutoDTO produtoDTOSalvo = ProdutoMapper.toDTOUpdate(produtoRepository.save(produto));
            return produtoDTOSalvo;
        });
    }

    // DELETE
    public boolean deleteProduto(Long id){
        Optional <Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            produtoRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }

     //Pesquisa com LIKE
     public List<ProdutoDTO> findByNomeContaining(String nome){
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);

        return produtos.stream()
        .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(),MarcaMapper.toDtoUpdate(produto.getMarca()),CategoriaMapper.toDTOUpdate(produto.getCategoria()), DepartamentoMapper.toDtoUpdate(produto.getCategoria().getDepartamento())))
        .collect(Collectors.toList());

    }
    

    
    
}



