package com.mycompany.trabalhoso.model;

public class Cliente {

    private int id;
    private String nome;
    private String CPF;

    public Cliente() {
    }

    public Cliente(String nome, String CPF) {
        this.id = 0;
        this.nome = nome;
        this.CPF = CPF;
    }

    public Cliente(int id, String nome, String CPF) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
    }

    public Cliente getCliente() {
        return this;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCliente(Cliente cliente){
        this.id=cliente.id;
        this.nome=cliente.nome;
        this.CPF=cliente.CPF;
    }

    public void setCliente(int id, String nome, String CPF){
        this.id=id;
        this.nome=nome;
        this.CPF=CPF;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setCPF(String CPF){
        this.CPF=CPF;
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%s", this.id, this.nome, this.CPF); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
