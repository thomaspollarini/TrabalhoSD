package com.mycompany.trabalhoso.control;

import java.util.List;
import com.mycompany.trabalhoso.model.*;


public class ServiceConta {

    public ServiceConta() {
    }

    public static boolean criarConta(Conta conta) {

        if(Verify.identificadorUnico(conta.getIdentificador()) && Verify.idClienteExiste(conta.getIdCliente())){
            return false;
        }

        conta.setSaldo(1000);
        conta.setId (getAllContas()
                .stream()
                .map(contas-> contas.getId())
                .max(Integer::compare)
                .orElse(0)
                +1);
        
        BancoDados.writeArqConta(conta);
        
        return true;
    }

    public static Conta getConta(int id) {
        return getAllContas().stream()
                .filter(conta -> conta.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Conta> getAllContas() {
        try {
            return BancoDados.readArqConta();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Contas");
            return null;
        }
    } 
    
}
