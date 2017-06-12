/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sti.model;

/**
 *
 * @author wilker
 */
public class Aluno {

    private String nome;
    private int matricula;
    private String telefone;
    private String email;
    private String uffmail;
    private String status;

    public Aluno(String nome, int matricula, String telefone, String email, String uffmail, String status) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.uffmail = uffmail;
        this.status = status;
    }

    public String[] generateEmail() {
        String[] options = new String[5];
        String[] nome = this.nome.toLowerCase().split(" ");
        options[0] = nome[0] + "_" + nome[1]+"@id.uff.br";
        options[1] = nome[0] + nome[1].charAt(0) + nome[2].charAt(0)+"@id.uff.br";
        options[2] = nome[0] + nome[2]+"@id.uff.br";
        options[3] = nome[0].charAt(0) + nome[2]+"@id.uff.br";
        options[4] = nome[0].charAt(0) + nome[1] + nome[2]+"@id.uff.br";

        return options;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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

    public String getUffmail() {
        return uffmail;
    }

    public void setUffmail(String uffmail) {
        this.uffmail = uffmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
