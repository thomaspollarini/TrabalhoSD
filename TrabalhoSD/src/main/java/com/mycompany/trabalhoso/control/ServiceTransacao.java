package com.mycompany.trabalhoso.control;

import java.io.IOException;
import java.util.List;

import com.mycompany.trabalhoso.model.*;


public class ServiceTransacao {

    public ServiceTransacao(){
    }
    //FALTA COISA AINDA, ATUALIZA CONTAS NO ARQUIVO
    public static boolean fazerTransacao(Transacao transacao) throws IOException{

        if(!Verify.idContaExiste(transacao.getIdContaSaida()) || !Verify.idContaExiste(transacao.getIdContaDestino())){
            return false;
        }
        

        if (ServiceConta.getConta(transacao.getIdContaSaida()).getSaldo() >= transacao.getValor()) {
            ServiceConta.getConta(transacao.getIdContaSaida()).setSaldo(ServiceConta.getConta(transacao.getIdContaSaida()).getSaldo() - transacao.getValor());
            ServiceConta.getConta(transacao.getIdContaDestino()).setSaldo(ServiceConta.getConta(transacao.getIdContaDestino()).getSaldo() + transacao.getValor());
            return true;
        }

        transacao.setId(BancoDados.readArqTransacao().stream()
        .map(transacoes
                -> transacoes.getId()
        ) // Extrai o ID de cada transacao
        .max(Integer::compare) // Encontra o maior ID
        .orElse(0)
        + 1);
        BancoDados.writeArq(transacao,"src/bd/transacoes");
        return true;
    }

    public Transacao getTransacao(int id) {
        return getAllTransacoes().stream()
                .filter(transacao -> transacao.getId() == id)
                .findFirst() // Retorna o primeiro transacao encontrado
                .orElse(null);
    }

    public static List<Transacao> getAllTransacoes() {
        //return new Transacao().readArqTransacao();
        try {
            return BancoDados.readArqTransacao();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Transacao");
            return null;
        }
    }
}

