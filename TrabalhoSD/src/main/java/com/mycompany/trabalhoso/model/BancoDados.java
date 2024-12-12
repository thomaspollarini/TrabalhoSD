/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoso.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class BancoDados {
    

    public static List<Cliente> readArqCliente() throws IOException {
        List<String> linhas = readAllLines(Paths.get("src/bd/clientes"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return new Cliente(Integer.parseInt(dados[0]), dados[1], dados[2]);
                })
                .collect(Collectors.toList());
    }

    public static void writeArqCliente(Cliente cliente) {
        try (FileWriter escritor = new FileWriter("src/bd/clientes", true)) {
            escritor.write(cliente.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo cliente");
        }
    }

    public static List<Conta> readArqConta() throws IOException {
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
    public static void writeArqConta(Conta conta) {
        try (FileWriter escritor = new FileWriter("src/bd/contas", true)) {
            escritor.write(conta.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo contas");
        }
    }

    public static List<Transacao> readArqTransacao() throws IOException {
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

    public static void writeArqTransacao(Transacao transacao) throws IOException {
        try (FileWriter escritor = new FileWriter("src/bd/transacoes", true)) {
            escritor.write(transacao.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo transacoes");
        }
    }
}
