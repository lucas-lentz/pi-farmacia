package com.farmacia.farmacia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.dto.MarcaDTO;
import com.farmacia.farmacia.entities.Marca;
import com.farmacia.farmacia.repository.MarcaRepository;
import com.farmacia.farmacia.util.MarcaMapper;

@Service
public class MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;

    public void setMarcaRepository(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    // CREATE
    public ResponseEntity <MarcaDTO> createMarca(MarcaDTO marcaDTO) {
        Marca marca = MarcaMapper.toEntityCreate(marcaDTO);
        MarcaDTO marcaDTOSalvo = MarcaMapper.toDtoCreate(marcaRepository.save(marca));

        return ResponseEntity.ok(marcaDTOSalvo);
    }

    // READ
    public List <MarcaDTO> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();

        return marcas.stream().map(marca -> 
        new MarcaDTO(marca.getId(), marca.getNome())).collect(Collectors.toList());
    }

    // READ BY ID
    public Optional <MarcaDTO> getMarcaById(Long id) {
        return marcaRepository.findById(id)
        .map(marca -> new MarcaDTO(marca.getId(), marca.getNome()));
    }

    // PUT
    public Optional <MarcaDTO> updateMarca(Long id, MarcaDTO marcaDTO) {
        return marcaRepository.findById(id).map(marca -> {
            // Atualiza somente os campos necessários
            marca = MarcaMapper.toEntityUpdate(marca, marcaDTO);
            MarcaDTO marcaDTOSalvo = MarcaMapper.toDtoUpdate(marcaRepository.save(marca));
            return marcaDTOSalvo;
        });
    }

    // DELETE
    public boolean deleteMarca(Long id) {
        Optional <Marca> marca = marcaRepository.findById(id);
        
        if (marca.isPresent()) {
            marcaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //Pesquisa com LIKE
    public List<MarcaDTO> findByNomeContaining(String nome){

        return marcaRepository.findByNomeContainingIgnoreCase(nome).stream()
               .map(marca -> new MarcaDTO(marca.getId(), marca.getNome()))
               .collect(Collectors.toList());
    }

}
