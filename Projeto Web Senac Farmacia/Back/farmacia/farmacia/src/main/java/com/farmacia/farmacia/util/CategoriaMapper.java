package com.farmacia.farmacia.util;
 
import com.farmacia.farmacia.dto.CategoriaDTO;
import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.entities.Categoria;
import com.farmacia.farmacia.entities.Departamento;
 
public class CategoriaMapper {
 
    public static CategoriaDTO toDTOCreate(Categoria categoria, DepartamentoDTO departamentoDTO) {
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(), categoria.getNome(), departamentoDTO);
        return categoriaDTO;
    }
 
    public static Categoria toEntityCreate(CategoriaDTO categoriaDTO, Departamento departamento) {
        Categoria categoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome(), departamento);
        return categoria;
    }
 
    /*
    public static CategoriaDTO toDTOUpdate(Categoria categoria, DepartamentoDTO departamentoDTO) {
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getNome(), departamentoDTO);
        return categoriaDTO;
    }
 
    public static Categoria toEntityUpdate(CategoriaDTO categoriaDTO, Departamento departamento) {
        Categoria categoria = new Categoria(categoriaDTO.getNome(), departamento);
        return categoria;
    }
    */
    public static CategoriaDTO toDTOUpdate(Categoria categoria) {
        // Incluindo ID e departamento no DTO
        DepartamentoDTO departamentoDTO = DepartamentoMapper.toDtoUpdate(categoria.getDepartamento());
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), departamentoDTO);
    }
    
    public static Categoria toEntityUpdate(Categoria categoria, CategoriaDTO categoriaDTO, Departamento departamento) {
        // Atualiza os atributos da categoria existente
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDepartamento(departamento);
        return categoria; // Retorna a categoria atualizada
    }
    

   
}