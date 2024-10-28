package com.farmacia.farmacia.dto;

public class DepartamentoDTO {

    private Long id;
    private String nome;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(String nome) {
        this.nome = nome;
    }

    public DepartamentoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
}
