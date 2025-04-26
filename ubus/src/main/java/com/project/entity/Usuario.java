package com.project.entity;

public class Usuario {
    private String nome;
    private String senha;

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    

    public Usuario() {
    }


    

    public void setNome(String nome) {
        this.nome = nome;
    }



    public void setSenha(String senha) {
        this.senha = senha;
    }



    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }
}
