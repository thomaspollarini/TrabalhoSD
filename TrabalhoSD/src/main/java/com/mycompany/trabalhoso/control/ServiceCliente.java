package com.mycompany.trabalhoso.control;

import com.mycompany.trabalhoso.model.*;

import java.io.IOException;
import java.rmi.Naming;
import java.util.List;

public class ServiceCliente {

    public ServiceCliente(){
    }

    public static boolean criarCliente(Cliente cliente) throws IOException{
        if(Verify.cpfUnico(cliente.getCPF()) && Verify.idClienteExiste(cliente.getId())){
            return false;
        }
        //ModelAPI bd = null;
        try {

            ModelAPI bd = (ModelAPI) Naming.lookup("rmi://localhost:1099/bancoDados");
        return bd.writeArq(cliente,"src/bd/clientes");

        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage()); 
            e.printStackTrace();
            return false;
        }
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
        //ModelAPI bd = null;
        try {
            ModelAPI bd = (ModelAPI) Naming.lookup("rmi://localhost:1099/bancoDados");
            return bd.readArqCliente();
        } catch (Exception e ) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage()); 
            e.printStackTrace();
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