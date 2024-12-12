package com.mycompany.trabalhoso.model;

public class Transacao {
    private int id;
    private double valor;
    private int idContaSaida;
    private int idContaDestino;

    public Transacao() {
    }

    public Transacao(double valor, int idContaSaida, int idContaDestino) {
        this.id = 0;
        this.valor = valor;
        this.idContaSaida = idContaSaida;
        this.idContaDestino = idContaDestino;
    }

    public Transacao(int id, double valor, int idContaSaida, int idContaDestino) {
        this.id = id;
        this.valor = valor;
        this.idContaSaida = idContaSaida;
        this.idContaDestino = idContaDestino;
    }

    public Transacao getTransacao() {
        return this;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public int getIdContaSaida() {
        return idContaSaida;
    }

    public int getIdContaDestino() {
        return idContaDestino;
    }

    public void setTransacao(Transacao transacao){
        this.id=transacao.id;
        this.valor=transacao.valor;
        this.idContaSaida=transacao.idContaSaida;
        this.idContaDestino=transacao.idContaDestino;
    }

    public void setTransacao(int id, double valor, int idContaSaida, int idContaDestino){
        this.id=id;
        this.valor=valor;
        this.idContaSaida=idContaSaida;
        this.idContaDestino=idContaDestino;
    }

    public void setTransacao(double valor, int idContaSaida, int idContaDestino){
        this.id=0;
        this.valor=valor;
        this.idContaSaida=idContaSaida;
        this.idContaDestino=idContaDestino;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setValor(double valor){
        this.valor=valor;
    }

    public void setIdContaSaida(int idContaSaida){
        this.idContaSaida=idContaSaida;
    }

    public void setIdContaDestino(int idContaDestino){
        this.idContaDestino=idContaDestino;
    }

    @Override
    public String toString() {
        return String.format("%d;%.2f;%d;%d",
                this.id,
                this.valor,
                this.idContaSaida,
                this.idContaDestino
        );
    }

}
