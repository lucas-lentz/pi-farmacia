package com.farmacia.farmacia.dto;

public class MarcaDTO {
    private Long id;
    private String nome;
    
    public MarcaDTO() {
    }

    public MarcaDTO(String nome) {
        this.nome = nome;
    }

    public MarcaDTO(Long id, String nome) {
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
