package com.project.entity;

public class Admin extends Usuario {
    private String email;
    
    public Admin(String nome, String senha, String email) {
        super(nome, senha);
        this.email = email;
    }

    public Admin(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin [email=" + email + "]";
    }
}