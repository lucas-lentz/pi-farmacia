package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{
    
    List<Marca> findByNomeContainingIgnoreCase(String nome);
}
