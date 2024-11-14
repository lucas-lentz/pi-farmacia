package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository <Departamento, Long> {

    List<Departamento> findByNomeContainingIgnoreCase(String nome);
    
}
