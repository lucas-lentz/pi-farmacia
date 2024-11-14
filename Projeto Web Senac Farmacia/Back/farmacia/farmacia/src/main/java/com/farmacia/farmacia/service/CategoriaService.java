package com.farmacia.farmacia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.dto.CategoriaDTO;
import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.entities.Categoria;
import com.farmacia.farmacia.entities.Departamento;
import com.farmacia.farmacia.repository.CategoriaRepository;
import com.farmacia.farmacia.repository.DepartamentoRepository;
import com.farmacia.farmacia.util.CategoriaMapper;
import com.farmacia.farmacia.util.DepartamentoMapper;

@Service
public class CategoriaService {
 
    @Autowired
    private CategoriaRepository categoriaRepository;
 
    @Autowired
    private DepartamentoRepository departamentoRepository;
 
    public ResponseEntity <CategoriaDTO> createCategoria(CategoriaDTO categoriaDTO) {
        //Busca o departamento no repository como entidade
        Departamento departamento = departamentoRepository.findById(categoriaDTO.getDepartamentoDTO().getId())
        .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
        //Converte a entidade departamento para DTO
        DepartamentoDTO departamentoDTO = DepartamentoMapper.toDtoCreate(departamento);
       
        //Converte categoriaDTO para entidade
        Categoria categoria = CategoriaMapper.toEntityCreate(categoriaDTO, departamento);
        //Salva e converte para DTO
        CategoriaDTO categoriaDTOSalvo = CategoriaMapper.toDTOCreate(categoriaRepository.save(categoria), departamentoDTO);
 
        return ResponseEntity.ok(categoriaDTOSalvo);
    }
 
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    //Pegar todos os departamentos
    public List<DepartamentoDTO> getAllDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return DepartamentoMapper.toDtoList(departamentos); // Converte a lista de entidades para DTO
    }

    public Optional<CategoriaDTO> updateCategoria(Long id, CategoriaDTO categoriaDTO /*DepartamentoDTO departamentoDTO*/){
        return categoriaRepository.findById(id).map(categoria -> {
            
            Departamento departamento = departamentoRepository.findById(categoriaDTO.getDepartamentoDTO().getId())
            .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
            //DepartamentoDTO departamentoDTOSalvo = DepartamentoMapper.toDtoUpdate(departamento);

            categoria = CategoriaMapper.toEntityUpdate(categoria, categoriaDTO, departamento);
            CategoriaDTO categoriaDTOSalvo = CategoriaMapper.toDTOUpdate(categoriaRepository.save(categoria));
            return categoriaDTOSalvo;

        });
        
    }
    public boolean deleteCategoria(Long id){
        Optional <Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()){
            categoriaRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }

     //Pesquisa com LIKE
     public List<CategoriaDTO> findByNomeContaining(String nome){
        List<Categoria> categorias = categoriaRepository.findByNomeContainingIgnoreCase(nome);

        return categorias.stream()
        .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(), DepartamentoMapper.toDtoUpdate(categoria.getDepartamento())))
        .collect(Collectors.toList());

    }

}