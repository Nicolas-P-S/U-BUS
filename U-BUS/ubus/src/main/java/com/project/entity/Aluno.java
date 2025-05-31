package com.project.entity;

public class Aluno extends Usuario {
    private String nome_Aluno;
    private String sobrenome_Aluno;
    private String cpf;                
    private String cep; 
    private String enderco;
    private String bairro;
    private String curso;
    private int semestre;
    private String turno;
    private String instituicao;       
    private String telefone;           
    private String email;
    private boolean vaiParaAula;
    
    


    public Aluno(String nome, String senha, String nome_Aluno, String sobrenome_Aluno, String cpf, String cep, String enderco, String bairro, String curso,
            int semestre, String turno, String instituicao, String telefone, String email) {
        super(nome, senha);
        this.nome_Aluno = nome_Aluno;
        this.sobrenome_Aluno = sobrenome_Aluno;
        this.cpf = cpf;
        this.cep = cep;
        this.enderco = enderco;
        this.bairro = bairro;
        this.curso = curso;
        this.semestre = semestre;
        this.turno = turno;
        this.instituicao = instituicao;
        this.telefone = telefone;
        this.email = email;
    }
    public Aluno() {
        
    }


    public String getNome_Aluno() {
        return nome_Aluno;
    }
    public void setNome_Aluno(String nome_Aluno) {
        this.nome_Aluno = nome_Aluno;
    }
    public String getSobrenome_Aluno() {
        return sobrenome_Aluno;
    }
    public void setSobrenome_Aluno(String sobrenome_Aluno) {
        this.sobrenome_Aluno = sobrenome_Aluno;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getEnderco() {
        return enderco;
    }
    public void setEnderco(String enderco) {
        this.enderco = enderco;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
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

    public boolean isVaiParaAula() {
        return vaiParaAula;
    }
    public void setVaiParaAula(boolean vaiParaAula) {
        this.vaiParaAula = vaiParaAula;
    }
    
    
    
    @Override
    public String toString() {
        return "Aluno {\n" +
            "  nome='" + getNome_Aluno() + " " + getSobrenome_Aluno() + "',\n" + 
            "  cpf='" + cpf + "',\n" +
            "  cep='" + cep + "',\n" +
            "  enderco='" + enderco + "',\n" +
            "  bairro='" + bairro + "',\n" +
            "  curso='" + curso + "',\n" +
            "  semestre='" + semestre + "',\n" +
            "  turno='" + turno + "',\n" +
            "  instituicao='" + instituicao + "',\n" +
            "  telefone='" + telefone + "',\n" +
            "  email='" + email + "'\n" +
            "}";
    }
    



    
    
}
