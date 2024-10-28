package com.farmacia.farmacia.util;

import com.farmacia.farmacia.dto.CategoriaDTO;
import com.farmacia.farmacia.dto.MarcaDTO;
import com.farmacia.farmacia.dto.ProdutoDTO;
import com.farmacia.farmacia.entities.Categoria;
import com.farmacia.farmacia.entities.Marca;
import com.farmacia.farmacia.entities.Produto;

public class ProdutoMapper {

    public static ProdutoDTO toDTOCreate(Produto produto, MarcaDTO marcaDTO, CategoriaDTO categoriaDTO) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getQuantidade(), marcaDTO, categoriaDTO);
    }

    public static Produto toEntityCreate(ProdutoDTO produtoDTO, Marca marca, Categoria categoria) {
        return new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(),
                produtoDTO.getQuantidade(), marca, categoria);
    }

    public static ProdutoDTO toDTOUpdate(Produto produto) {
        MarcaDTO marcaDTO = MarcaMapper.toDtoUpdate(produto.getMarca());
        CategoriaDTO categoriaDTO = CategoriaMapper.toDTOUpdate(produto.getCategoria());
        ProdutoDTO produtoDto = new ProdutoDTO(produto.getNome(), produto.getDescricao(), produto.getPreco(),produto.getQuantidade(),marcaDTO,categoriaDTO);
        return produtoDto;
    }
    /*
    public static Produto toEntityUpdate(Produto produto, ProdutoDTO produtoDto, Marca marca, Categoria categoria) {
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setMarca(marca);
        produto.setCategoria(categoria);
        return produto;
    }
    */

    public static Produto toEntityUpdate(Produto produto, ProdutoDTO produtoDto, Marca marca, Categoria categoria) {
        if (produto == null || produtoDto == null || marca == null || categoria == null) {
            throw new IllegalArgumentException("Produto, ProdutoDTO, Marca e Categoria não podem ser nulos.");
        }
        
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setMarca(marca);
        produto.setCategoria(categoria);
        
        return produto;
    }
    

}
