package com.mycompany.trabalhoso.control;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.mycompany.trabalhoso.model.*;


public class SystemOperation extends UnicastRemoteObject implements ControlAPI {
    
    public SystemOperation()throws RemoteException {
        super();
    }

    public String consultarSaldo(int idConta) throws RemoteException {
        return "Saldo atual: R$" + ServiceConta.getConta(idConta).getSaldo();
    }

    public String transferir(int idContaSaida, int idContaDestino, double valor) throws IOException, RemoteException {
        if(valor<=0){
            return "ERRO: Valor inválido!";
        }
        Transacao transacao = new Transacao(ServiceTransacao.getNextId(), valor, idContaSaida, idContaDestino);
        return ServiceTransacao.fazerTransacao(transacao);
    }

    public String consultarExtrato(int idConta) throws RemoteException{
        StringBuilder result = new StringBuilder();
        ServiceTransacao.getAllTransacoes().stream()
                .filter(transacao -> transacao.getIdContaSaida() == idConta || transacao.getIdContaDestino() == idConta)
                .forEach(transacao -> {
                    if (transacao.getIdContaSaida() == idConta) {
                        result.append("Transferência feita para conta " + transacao.getIdContaDestino()
                                + " no valor de R$" + transacao.getValor() + "\n");
                    } else {
                        result.append("Transferência recebida da conta " + transacao.getIdContaDestino()
                                + " no valor de R$" + transacao.getValor() + "\n");
                    }
                });

        if (result.length() == 0) {
            result.append("Nenhuma transferência registrada");
        }

        return result.toString();

    }

    public String criarConta(Cliente cliente, Conta conta) throws IOException, RemoteException{

        cliente.setId(ServiceCliente.getNextId());

        if (!ServiceCliente.criarCliente(cliente)) {
            return "CPF já está cadastrado";
        }

        conta.setIdCliente(cliente.getId());
        return ServiceConta.criarConta(conta) ? "Conta criada com sucesso" : "Identificador já está cadastrado";
    }

    public boolean realizarLogin(String identificador, String senha) throws RemoteException {
        return ServiceConta.realizarLogin(identificador, senha);
    }

    public String consultarMontante() throws RemoteException{
        return "Montante total: R$"
                + ServiceConta.getAllContas()
                        .stream()
                        .mapToDouble(Conta::getSaldo)
                        .sum()
                + "  Nº de contas: "
                + ServiceConta.getAllContas().size();
    }

    public String pesquisarContas(String nome) throws RemoteException {
        StringBuilder result = new StringBuilder();
        ServiceCliente.getAllClientes().stream()
                .filter(cliente -> cliente.getNome().contains(nome))
                .forEach(cliente -> {
                    result.append("Nome: " + cliente.getNome()
                            + "  CPF: " + cliente.getCPF() + " ID CONTA: "
                            + ServiceConta.getContaCliente(cliente.getId()).getId() + "\n");
                });

        if (result.length() == 0) {
            result.append("Nenhuma conta encontrada");
        }

        return result.toString();
    }
    
    public static void main(String[] args) {
        try {
            //LocateRegistry.createRegistry(1099);
            SystemOperation control = new SystemOperation();
            
            Naming.rebind("rmi://localhost/control",control);
            System.out.println("SERVIDOR DE CONCROLE LIGADO!!");
            System.out.println("Servidor >> ligado no registro RMI sob o nome 'control' ");
        }
        catch (Exception e) {
            System.out.println("ERRO: servidor controle  " + e.getMessage()); 
            e.printStackTrace(); 
        }
    }

}
