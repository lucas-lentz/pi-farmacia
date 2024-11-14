package com.farmacia.farmacia.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.farmacia.farmacia.entities.Categoria;
 
@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {

       List<Categoria> findByNomeContainingIgnoreCase(String nome);

}