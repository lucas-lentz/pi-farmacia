package com.farmacia.farmacia.controller;

import java.util.*;

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
import com.farmacia.farmacia.dto.MarcaDTO;
import com.farmacia.farmacia.service.MarcaService;

@RestController
@CrossOrigin(origins = "http://localhost:8080/HTML/marca.html")
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public ResponseEntity <MarcaDTO> addMarca(@RequestBody MarcaDTO marcaDTO) {
        return marcaService.createMarca(marcaDTO);
    }

    @GetMapping
    public List<MarcaDTO> getMarcas() {
        return marcaService.getAllMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity <MarcaDTO> getMarcaById(@PathVariable Long id) {
        return marcaService.getMarcaById(id).map(marca -> ResponseEntity.ok(marca)).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteMarcaById(@PathVariable Long id) {
        boolean itemDeletado = marcaService.deleteMarca(id);

        if (itemDeletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <MarcaDTO> updateMarca(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
        Optional <MarcaDTO> updatedMarca = marcaService.updateMarca(id, marcaDTO);

        return updatedMarca.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Com LIKE
    @GetMapping("/search")
    public ResponseEntity<List<MarcaDTO>> searchMarcas(@RequestParam String nome) {
        List<MarcaDTO> marcas = marcaService.findByNomeContaining(nome);
        return ResponseEntity.ok(marcas);
    }


}
