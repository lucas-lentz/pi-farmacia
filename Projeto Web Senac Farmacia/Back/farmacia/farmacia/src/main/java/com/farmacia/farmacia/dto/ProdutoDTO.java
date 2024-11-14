package com.farmacia.farmacia.dto;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private MarcaDTO marcaDTO;
    private CategoriaDTO categoriaDTO;
    private DepartamentoDTO departamentoDTO; // Para a pesquisa dinâmina LIKE

    // Construtor vazio
    public ProdutoDTO() {
    }
    // Construtor SEM departamento
    public ProdutoDTO(Long id, String nome, String descricao, double preco, int quantidade, MarcaDTO marcaDTO,
            CategoriaDTO categoriaDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marcaDTO = marcaDTO;
        this.categoriaDTO = categoriaDTO;
    }

    // Construtor sem categoria e sem departamento
    public ProdutoDTO(Long id, String nome, String descricao, double preco, int quantidade, MarcaDTO marcaDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marcaDTO = marcaDTO;
    }

    // Construtor sem id e sem departamento
    public ProdutoDTO(String nome, String descricao, double preco, int quantidade, MarcaDTO marcaDTO,
            CategoriaDTO categoriaDTO) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marcaDTO = marcaDTO;
        this.categoriaDTO = categoriaDTO;
    }

    // Construtor completo
    public ProdutoDTO(Long id, String nome, String descricao, double preco, int quantidade, MarcaDTO marcaDTO,
            CategoriaDTO categoriaDTO, DepartamentoDTO departamentoDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marcaDTO = marcaDTO;
        this.categoriaDTO = categoriaDTO;
        this.departamentoDTO = departamentoDTO;
    }

    // Construtor sem descrição, sem preco, sem quantidade, para pesquisa like
    public ProdutoDTO(Long id, String nome, MarcaDTO marcaDTO, CategoriaDTO categoriaDTO,
            DepartamentoDTO departamentoDTO) {
        this.id = id;
        this.nome = nome;
        this.marcaDTO = marcaDTO;
        this.categoriaDTO = categoriaDTO;
        this.departamentoDTO = departamentoDTO;
    }
    // Getters e Setters
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

    public MarcaDTO getMarcaDTO() {
        return marcaDTO;
    }

    public void setMarcaDTO(MarcaDTO marcaDTO) {
        this.marcaDTO = marcaDTO;
    }

    public CategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }

    public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
        this.categoriaDTO = categoriaDTO;
    }

}
