package com.farmacia.farmacia.controller;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.farmacia.farmacia.dto.CategoriaDTO;
import com.farmacia.farmacia.dto.DepartamentoDTO;
import com.farmacia.farmacia.entities.Categoria;
import com.farmacia.farmacia.service.CategoriaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


 
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/categorias")
public class CategoriaController {
 
    @Autowired
    private CategoriaService categoriaService;
 
    @PostMapping
    public ResponseEntity <CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.createCategoria(categoriaDTO);
    }

    @GetMapping
    public List<Categoria> getCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/departamentos")
    public List<DepartamentoDTO> getDepartamentos() {
        return categoriaService.getAllDepartamentos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO, DepartamentoDTO departamentoDTO) {
        Optional<CategoriaDTO> updateCategoria = categoriaService.updateCategoria(id, categoriaDTO/*, departamentoDTO*/);
        return updateCategoria.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id){
        boolean itemDeletado = categoriaService.deleteCategoria(id);
        if (itemDeletado){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoriaDTO>> searchCategorias(@RequestParam String nome) {
        List<CategoriaDTO> categorias = categoriaService.findByNomeContaining(nome);
        return ResponseEntity.ok(categorias);
    }
    


 
}