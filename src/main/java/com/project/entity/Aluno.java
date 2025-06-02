package com.project.entity;
import java.util.Random; 

public class Aluno extends Usuario {
    private String nome_Aluno;
    private String sobrenome_Aluno;
    private String cpf;                
    private String cep; 
    private String endereco;
    private String bairro;
    private String curso;
    private int semestre;
    private String turno;
    private String instituicao;       
    private String telefone;           
    private String email;
    private boolean vaiParaAula;

    public Aluno(){}

    public Aluno(String nome, String senha, String nome_Aluno, String sobrenome_Aluno, String cpf, String cep, String endereco, String bairro, String curso,
            int semestre, String turno, String instituicao, String telefone, String email) {
        super(nome, senha);
        this.nome_Aluno = nome_Aluno;
        this.sobrenome_Aluno = sobrenome_Aluno;
        this.cpf = cpf;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.curso = curso;
        this.semestre = semestre;
        this.turno = turno;
        this.instituicao = instituicao;
        this.telefone = telefone;
        this.email = email;
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
    public String getEndereco() {
        return endereco;
    }
    public void setEnderco(String endereco) {
        this.endereco = endereco;
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
            "  enderco='" + endereco + "',\n" +
            "  bairro='" + bairro + "',\n" +
            "  curso='" + curso + "',\n" +
            "  semestre='" + semestre + "',\n" +
            "  turno='" + turno + "',\n" +
            "  instituicao='" + instituicao + "',\n" +
            "  telefone='" + telefone + "',\n" +
            "  email='" + email + "'\n" +
            "}";
    }

    public void gerarInfos() {
        Random random = new Random();

        String[] nomes = {
            "Ana", "Bruno", "Camila", "Daniel", "Elisa",
            "Felipe", "Gabriela", "Henrique", "Isabela", "João",
            "Karina", "Leonardo", "Mariana", "Nicolas", "Olivia",
            "Pedro", "Queila", "Rafael", "Sofia", "Tiago", "Antonio",
            "Úrsula", "Vinícius", "Wanda", "Xavier", "Yara", "Kayky"
        };

        String[] sobrenomes = {
            "Almeida", "Barbosa", "Castro", "Duarte", "Esteves",
            "Ferreira", "Gonçalves", "Henrique", "Iglesias", "Jardim",
            "Kuhlmann", "Lima", "Martins", "Nogueira", "Oliveira", "José",
            "Pacheco", "Queiroz", "Ribeiro", "Silva", "Teixeira", "Cajui",
            "Uchoa", "Vieira", "Wolf", "Xavier", "Zanetti", "Augusto", "Veiga"
        };

        String[] cursos = {
            "Sistemas de Informação", "Direito", "Enfermagem", "Engenharia Civil", 
            "Psicologia", "Administração", "Ciências da Computação", "Pedagogia"
        };

        String[] turnos = {"Manhã", "Tarde", "Noite"};

        String[] instituicoes = {"UEPB", "FIP", "UFCG", "UNIPLAN", "IFPB"};

        String[] bairros = {"Centro", "Catolé", "Pereiros", "Altiplano", "Petrópolis", "Nova Vida", "Vida Nova"};

        // Nomes
        this.nome_Aluno = nomes[random.nextInt(nomes.length)];
        this.sobrenome_Aluno = sobrenomes[random.nextInt(sobrenomes.length)];

        // CPF simulado
        this.cpf = String.format("%03d.%03d.%03d-%02d",
            random.nextInt(1000), random.nextInt(1000), random.nextInt(1000), random.nextInt(100));

        // CEP simulado
        this.cep = String.format("%05d-%03d", random.nextInt(90000) + 10000, random.nextInt(1000));

        // Endereço simples
        this.endereco = "Rua " + sobrenomes[random.nextInt(sobrenomes.length)] + ", Nº " + (random.nextInt(500) + 1);

        // Bairro
        this.bairro = bairros[random.nextInt(bairros.length)];

        // Curso
        this.curso = cursos[random.nextInt(cursos.length)];

        // Semestre
        this.semestre = random.nextInt(10) + 1;

        // Turno
        this.turno = turnos[random.nextInt(turnos.length)];

        // Instituição
        this.instituicao = instituicoes[random.nextInt(instituicoes.length)];

        // Telefone simulado
        this.telefone = String.format("(83) 9%04d-%04d", random.nextInt(10000), random.nextInt(10000));

        // Email
        String emailBase = (nome_Aluno + "." + sobrenome_Aluno + random.nextInt(100)).toLowerCase();
        this.email = emailBase + "@email.com";

        // Nome de usuário e senha (herdado de Usuario)
        this.setNome(emailBase);
        this.setSenha("senha123");

        // Vai para aula aleatoriamente
        this.vaiParaAula = random.nextBoolean();
    }

}