package com.farmacia.farmacia.entities;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "categorias")
public class Categoria {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
 
    @ManyToOne
    @JoinColumn(name = "departamentos_id")
    private Departamento departamento;
 
    public Categoria() {
    }
 
    public Categoria(String nome, Departamento departamento) {
        this.nome = nome;
        this.departamento = departamento;
    }
 
    public Categoria(Long id, String nome, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public Departamento getDepartamento() {
        return departamento;
    }
 
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
 
   
}