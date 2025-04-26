package com.project.entity;

public class Admin extends Usuario {
    private String email;
    private String tipo;
    


    public Admin(String nome, String senha, String email, String tipo) {
        super(nome, senha);
        this.email = email;
        this.tipo = tipo;
    }

    public Admin() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Admin [email=" + email + ", tipo=" + tipo + "]";
    }

    
    
}
