package com.project.entity;

public class Instituicao {
    private String nome;
    private int id;

    public Instituicao(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public Instituicao(){}

    public String getNome(){
        return nome;
    }

    public int getId(){
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
