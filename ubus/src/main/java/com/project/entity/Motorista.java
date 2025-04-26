package com.project.entity;

public class Motorista extends Usuario {
                           
    private String cpf;                
    private String cnh;                
    private String categoriaCNH;       
    private String telefone;           
    private String email;              
    private String endereco; 
    
    public Motorista(String nome, String senha,  String cpf, String cnh, String categoriaCNH, String telefone, String email, String endereco) {
        super(nome, senha); 
        this.cpf = cpf;
        this.cnh = cnh;
        this.categoriaCNH = categoriaCNH;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "-------- Dados do Motorista --------\n" +
            "Nome: " + getNome() + "\n" +
            "CPF: " + cpf + "\n" +
            "CNH: " + cnh + "\n" +
            "Categoria CNH: " + categoriaCNH + "\n" +
            "Telefone: " + telefone + "\n" +
            "Email: " + email + "\n" +
            "Endereço: " + endereco + "\n";
    }

    
}
