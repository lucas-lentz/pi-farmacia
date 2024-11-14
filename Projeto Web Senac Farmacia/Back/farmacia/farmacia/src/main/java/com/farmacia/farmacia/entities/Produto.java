package com.farmacia.farmacia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    private String descricao;
    @Column(nullable = false)
    private double preco;
    @Column(nullable = true)
    @Min(0)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "marcas_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "categorias_id")
    private Categoria categoria;

    // Construtor vazio
    public Produto() {
    }
    // Construtor completo
    public Produto(Long id, String nome, String descricao, double preco, int quantidade, Marca marca,
            Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marca = marca;
        this.categoria = categoria;
    }
    // Construtor sem categoria
    public Produto(Long id, String nome, String descricao, double preco, int quantidade, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }
    // EXCLUI O SET ID, POIS NAO PODE ALTERAR O ID

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
