package com.mycompany.trabalhoso.control;

import java.io.IOException;

import com.mycompany.trabalhoso.model.*;

public class SystemOperation {

    public SystemOperation() {
    }

    public static String consultarSaldo(int idConta) {
        return "Saldo atual: R$" + ServiceConta.getConta(idConta).getSaldo();
    }

    public static String transferir(int idContaSaida, int idContaDestino, double valor) throws IOException {
        if (valor <= 0) {
            return "ERRO: Valor inválido!";
        }
        Transacao transacao = new Transacao(ServiceTransacao.getNextId(), valor, idContaSaida, idContaDestino);
        return ServiceTransacao.fazerTransacao(transacao);
    }

    public static String consultarExtrato(int idConta) {
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

    public static String criarConta(Cliente cliente, Conta conta) throws IOException {

        cliente.setId(ServiceCliente.getNextId());

        if (!ServiceCliente.criarCliente(cliente)) {
            return "CPF já está cadastrado";
        }

        conta.setIdCliente(cliente.getId());
        return ServiceConta.criarConta(conta) ? "Conta criada com sucesso" : "Identificador já está cadastrado";
    }

    public boolean realizarLogin(String identificador, String senha) {
        return ServiceConta.realizarLogin(identificador, senha);
    }

    public String consultarMontante() {
        return "Montante total: R$"
                + ServiceConta.getAllContas()
                        .stream()
                        .mapToDouble(Conta::getSaldo)
                        .sum()
                + "  Nº de contas: "
                + ServiceConta.getAllContas().size();
    }

    public String pesquisarContas(String nome) {
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

}
