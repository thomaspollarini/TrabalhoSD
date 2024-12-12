package com.mycompany.trabalhoso.control;

import java.io.IOException;
import java.util.List;

import com.mycompany.trabalhoso.model.*;


public class ServiceTransacao {

    public ServiceTransacao(){
    }

    public static String fazerTransacao(Transacao transacao) throws IOException{
        Conta cSaida = ServiceConta.getConta(transacao.getIdContaSaida());
        Conta cDestino = ServiceConta.getConta(transacao.getIdContaDestino());

        if(cSaida==null || cDestino==null){
            return "ERRO: Identificador não encontrado no sistema!";
        }

        if(!atualizarSaldos(cSaida,cDestino,transacao.getValor())){
            return "ERRO: Saldo insuficiente!";
        }

        return BancoDados.writeArq(transacao,"src/bd/transacoes") ? "Transferência feita com sucesso" : "ERRO: Transferência falhou";
    }
    
    private static boolean atualizarSaldos(Conta cSaida, Conta cDestino, double valor){

        if (cSaida.getSaldo() > valor) {
            cSaida.setSaldo(cSaida.getSaldo() - valor);
            cDestino.setSaldo(cDestino.getSaldo() + valor);
        }else{
            return false;
        }
        
        return ServiceConta.atualizarConta(cSaida) && ServiceConta.atualizarConta(cDestino);

    }

    public static Transacao getTransacao(int id) {
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

    public static int getNextId(){
        return getAllTransacoes()
        .stream()
        .map(transacoes
                -> transacoes.getId()
        ) // Extrai o ID de cada transacao
        .max(Integer::compare) // Encontra o maior ID
        .orElse(0)
        + 1;
    }
}

