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

import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.service.DepartamentoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping
    public ResponseEntity <DepartamentoDTO> addDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        return departamentoService.createDepartamento(departamentoDTO);
    }

    @GetMapping
    public List<DepartamentoDTO> getDepartamentos() {
        return departamentoService.getAllDepartamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <DepartamentoDTO> getDepartamentoById(@PathVariable Long id) {
        return departamentoService.getDepartamentoById(id).map(departamento -> ResponseEntity.ok(departamento)).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteDepartamentoById(@PathVariable Long id) {
        boolean itemDeletado = departamentoService.deleteDepartamento(id);

        if (itemDeletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <DepartamentoDTO> updateDepartamento(@PathVariable Long id, @RequestBody DepartamentoDTO departamentoDTO) {
        Optional <DepartamentoDTO> updatedDepartamento = departamentoService.updateDepartamento(id, departamentoDTO);

        return updatedDepartamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Com LIKE
    @GetMapping("/search")
    public ResponseEntity<List<DepartamentoDTO>> searchDepartments(@RequestParam String nome) {
        List<DepartamentoDTO> departamentos = departamentoService.findByNomeContaining(nome);
        return ResponseEntity.ok(departamentos);
    }


}
