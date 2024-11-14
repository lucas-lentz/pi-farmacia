package com.farmacia.farmacia.dto;
 
public class CategoriaDTO {
 
    private Long id;
    private String nome;
    private DepartamentoDTO departamentoDTO;
 
    public CategoriaDTO() {
    }
   
    public CategoriaDTO(String nome, DepartamentoDTO departamentoDTO) {
        this.nome = nome;
        this.departamentoDTO = departamentoDTO;
    }
 
    public CategoriaDTO(Long id, String nome, DepartamentoDTO departamentoDTO) {
        this.id = id;
        this.nome = nome;
        this.departamentoDTO = departamentoDTO;
    }

    //Sem departamento(LIKE)
    
 
    public Long getId() {
        return id;
    }
 
    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
 
    public DepartamentoDTO getDepartamentoDTO() {
        return departamentoDTO;
    }
 
    public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
        this.departamentoDTO = departamentoDTO;
    }
 
}