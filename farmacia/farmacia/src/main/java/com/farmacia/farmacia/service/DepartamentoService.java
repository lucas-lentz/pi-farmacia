package com.farmacia.farmacia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.entities.Departamento;
import com.farmacia.farmacia.repository.DepartamentoRepository;
import com.farmacia.farmacia.util.DepartamentoMapper;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public ResponseEntity <DepartamentoDTO> createDepartamento(DepartamentoDTO departamentoDTO) {
        Departamento departamento = DepartamentoMapper.toEntityCreate(departamentoDTO);
        DepartamentoDTO departamentoDTOSalvo = DepartamentoMapper.toDtoCreate(departamentoRepository.save(departamento));

        return ResponseEntity.ok(departamentoDTOSalvo);
    }

    public List <DepartamentoDTO> getAllDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();

        return departamentos.stream().map(departamento -> 
        new DepartamentoDTO(departamento.getId(), departamento.getNome())).collect(Collectors.toList());
    }

    public Optional <DepartamentoDTO> getDepartamentoById(Long id) {
        return departamentoRepository.findById(id)
        .map(departamento -> new DepartamentoDTO(departamento.getId(), departamento.getNome()));
    }

    public Optional <DepartamentoDTO> updateDepartamento(Long id, DepartamentoDTO departamentoDTO) {
        return departamentoRepository.findById(id).map(departamento -> {
            // Atualiza somente os campos necessários
            departamento = DepartamentoMapper.toEntityUpdate(departamento, departamentoDTO);
            DepartamentoDTO departamentoDTOSalvo = DepartamentoMapper.toDtoUpdate(departamentoRepository.save(departamento));
            return departamentoDTOSalvo;
        });
    }

    public boolean deleteDepartamento(Long id) {
        Optional <Departamento> departamento = departamentoRepository.findById(id);
        
        if (departamento.isPresent()) {
            departamentoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //Pesquisa com LIKE
    public List<DepartamentoDTO> findByNomeContaining(String nome){

        return departamentoRepository.findByNomeContainingIgnoreCase(nome).stream()
               .map(departamento -> new DepartamentoDTO(departamento.getId(), departamento.getNome()))
               .collect(Collectors.toList());
    }


}
