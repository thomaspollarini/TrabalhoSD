package com.mycompany.trabalhoso.control;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.mycompany.trabalhoso.model.Cliente;
import com.mycompany.trabalhoso.model.Conta;

public interface ControlAPI extends Remote {
    public String consultarSaldo(int idConta)throws RemoteException;
        public String transferir(int idContaSaida, int idContaDestino, double valor) throws IOException, RemoteException;
        public String consultarExtrato(int idConta) throws RemoteException;
        public String criarConta(Cliente cliente, Conta conta) throws IOException, RemoteException;

    
}
