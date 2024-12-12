package com.mycompany.trabalhoso.control;

import com.mycompany.trabalhoso.model.*;

import java.io.IOException;
import java.util.List;

public class ServiceCliente {

    public ServiceCliente(){
    }

    public static boolean criarCliente(Cliente cliente) throws IOException{
        if(Verify.cpfUnico(cliente.getCPF()) && Verify.idClienteExiste(cliente.getId())){
            return false;
        }
        return BancoDados.writeArq(cliente,"src/bd/clientes");
    }
    
    public static Cliente getCliente(int id) {
        return getAllClientes().stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst() // Retorna o primeiro cliente encontrado
                .orElse(null);
    }

    public static Cliente getCliente(String cpf) {
        return getAllClientes().stream()
                .filter(cliente -> cliente.getCPF().equals(cpf))
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

    public static int getNextId(){
        return getAllClientes()
                .stream()
                .map(clientes-> clientes.getId())
                .max(Integer::compare)
                .orElse(0)
                +1;
    }
}