/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoso.bd;

/**
 *
 * @author 0064232
 */
public class BancoDados {
    

    static public List<Cliente> readArqCliente() throws IOException {
        List<String> linhas = readAllLines(Paths.get("src/bd/clientes"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return new Cliente(Integer.parseInt(dados[0]), dados[1], dados[2]);
                })
                .collect(Collectors.toList());
    }

    static public void writeArqCliente(Cliente cliente) {
        try (FileWriter escritor = new FileWriter("src/bd/clientes", true)) {
            escritor.write(cliente.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo cliente");
        }
    }
}
