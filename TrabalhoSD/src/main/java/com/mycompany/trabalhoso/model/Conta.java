package com.mycompany.trabalhoso.model;

import java.io.Serializable;

public class Conta implements Serializable{
    private int id;
    private double saldo;
    private String identificador;
    private String senha;
    private int idCliente;


    public Conta() {
    }

    public Conta(double saldo, String identificador, String senha, int idCliente) {
        this.id = 0;
        this.saldo = saldo;
        this.identificador = identificador;
        this.senha = senha;
        this.idCliente = idCliente;
    }

    public Conta(int id, double saldo, String identificador, String senha, int idCliente) {
        this.id = id;
        this.saldo = saldo;
        this.identificador = identificador;
        this.senha = senha;
        this.idCliente = idCliente;
    }

    public Conta getConta() {
        return this;
    }

    public int getId() {
        return id;
    }
    

    public double getSaldo() {
        return saldo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setConta(Conta conta){
        this.id=conta.id;
        this.saldo=conta.saldo;
        this.identificador=conta.identificador;
        this.senha=conta.senha;
        this.idCliente=conta.idCliente;
    }

    public void setConta(int id, double saldo, String identificador, String senha, int idCliente){
        this.id=id;
        this.saldo=saldo;
        this.identificador=identificador;
        this.senha=senha;
        this.idCliente=idCliente;
    }

    public void setConta(double saldo, String identificador, String senha, int idCliente){
        this.id=0;
        this.saldo=saldo;
        this.identificador=identificador;
        this.senha=senha;
        this.idCliente=idCliente;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setSaldo(double saldo){
        this.saldo=saldo;
    }

    public void setIdentificador(String identificador){
        this.identificador=identificador;
    }

    public void setSenha(String senha){
        this.senha=senha;
    }

    public void setIdCliente(int idCliente){
        this.idCliente=idCliente;
    }
    

public String toString() {
        return String.format("%d;%.2f%,%s;%s;%d",
                this.id, 
                this.saldo,
                this.identificador,
                this.senha,
                this.idCliente
        ); 
}

}
