/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoso.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDados extends UnicastRemoteObject implements ModelAPI{

    public BancoDados()throws RemoteException {
        super();
    }

    

    public <T> boolean writeArq(T objeto, String caminhoArquivo) throws RemoteException {
        try (FileWriter escritor = new FileWriter(caminhoArquivo, true)) {
            escritor.write(objeto.toString() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Erro na escrita do arquivo: " + caminhoArquivo);
            return false;
        }
    }

    /* 
    public static void writeArqCliente(Cliente cliente) {
        try (FileWriter escritor = new FileWriter("src/bd/clientes", true)) {
            escritor.write(cliente.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo cliente");
    }

    public static void writeArqConta(Conta conta) {
        try (FileWriter escritor = new FileWriter("src/bd/contas", true)) {
            escritor.write(conta.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo contas");
    }

    public static void writeArqTransacao(Transacao transacao) throws IOException {
        try (FileWriter escritor = new FileWriter("src/bd/transacoes", true)) {
            escritor.write(transacao.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo transacoes");
        }
    }
    
    */

    
    public List<Cliente> readArqCliente() throws IOException, RemoteException {
        List<String> linhas = readAllLines(Paths.get("src/bd/clientes"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return new Cliente(Integer.parseInt(dados[0]), dados[1], dados[2]);
                })
                .collect(Collectors.toList());
    }

    public List<Conta> readArqConta() throws IOException, RemoteException{
        List<String> linhas = readAllLines(Paths.get("src/bd/contas"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return  new Conta(
                            Integer.parseInt(dados[0]),
                            Double.parseDouble(dados[1]),
                            dados[2],
                            dados[3],
                            Integer.parseInt(dados[4])
                    );
                })
                .collect(Collectors.toList());
    }

    
    

    public List<Transacao> readArqTransacao() throws IOException {
        List<String> linhas = readAllLines(Paths.get("src/bd/transacoes"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return new Transacao(
                            Integer.parseInt(dados[0]),
                            Double.parseDouble(dados[1]),
                            Integer.parseInt(dados[2]),
                            Integer.parseInt(dados[3])
                    );
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            //LocateRegistry.createRegistry(1099);
            BancoDados bd = new BancoDados();
            
            Naming.rebind("rmi://localhost/bancoDados", bd);
            System.out.println("BANCO DE DADOS LIGADO!!");
            System.out.println("Servidor >> ligado no registro RMI sob o nome 'bancoDados' ");
    }
    catch (Exception e) {
        System.out.println("ERRO: Banco de dados " + e.getMessage()); 
        e.printStackTrace(); 
        }
    }
    
}
