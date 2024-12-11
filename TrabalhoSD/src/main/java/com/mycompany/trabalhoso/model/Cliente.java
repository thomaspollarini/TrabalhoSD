package com.mycompany.trabalhoso.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Cliente {

    private int id;
    private String nome;
    private String CPF;

    public Cliente() {
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

    public Cliente setCliente(Cliente cliente){
        this.id=cliente.id;
        this.nome=cliente.nome;
        this.CPF=cliente.CPF;
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

    
    public boolean setCliente(String nome, String CPF) throws IOException {
        this.nome = nome;
        this.CPF = CPF;
        this.id = Cliente.readArqCliente().stream()
                .map(cliente
                        -> cliente.getId()
                ) // Extrai o ID de cada cliente
                .max(Integer::compare) // Encontra o maior ID
                .orElse(0)
                + 1;
        Cliente.writeArqCliente(this);
        return true;
    }

    public Cliente getCliente(int id) {
        return getAllClientes().stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst() // Retorna o primeiro cliente encontrado
                .orElse(null);
    }

    static public List<Cliente> getAllClientes() {
        //return new Cliente().readArqCliente();
        try {
            return new Cliente().readArqCliente();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Cliente");
            return null;
        }
    }

    

    @Override
    public String toString() {
        return String.format("%d;%s;%s", this.id, this.nome, this.CPF); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
