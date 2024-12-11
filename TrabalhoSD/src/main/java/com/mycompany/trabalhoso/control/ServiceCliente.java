package com.mycompany.trabalhoso.control;

import com.mycompany.trabalhoso.model.*;

import java.io.IOException;
import java.util.List;

public class ServiceCliente {

    public ServiceCliente(){
    }

    public static boolean inserir(Cliente cliente) throws IOException{
        cliente.setId(BancoDados.readArqCliente().stream()
        .map(clientes
                -> clientes.getId()
        ) // Extrai o ID de cada cliente
        .max(Integer::compare) // Encontra o maior ID
        .orElse(0)
        + 1);
        BancoDados.writeArqCliente(cliente);
        return true;
    }
    
    public Cliente getCliente(int id) {
        return getAllClientes().stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst() // Retorna o primeiro cliente encontrado
                .orElse(null);
    }

    public static List<Cliente> getAllClientes() {
        //return new Cliente().readArqCliente();
        try {
            return BancoDados.readArqCliente();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Cliente");
            return null;
        }
    }
}