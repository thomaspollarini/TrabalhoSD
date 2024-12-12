package com.mycompany.trabalhoso.control;

import java.io.IOException;

import com.mycompany.trabalhoso.model.*;


public class SystemOperation {
    
    public SystemOperation() {
    }

    public static double consultarSaldo(int idConta) {
        return ServiceConta.getConta(idConta).getSaldo();
    }

    //FALTA ESPERA
    public static boolean transferir(int idContaSaida, int idContaDestino, double valor) {
        
        
        return false;
    }

    public static String criarConta(Cliente cliente, Conta conta) throws IOException{
    
        if(!ServiceCliente.criarCliente(cliente)){
            return "CPF já está cadastrado";
        }
        //VAI MUDAR ISSO DPS
        conta.setIdCliente(ServiceCliente.getCliente(cliente.getCPF()).getId());

        return ServiceConta.criarConta(conta) ? "Conta criada com sucesso" : "Identificador já está cadastrado";
    }


}
