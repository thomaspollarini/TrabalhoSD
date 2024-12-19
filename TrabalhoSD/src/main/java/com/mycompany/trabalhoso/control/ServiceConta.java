package com.mycompany.trabalhoso.control;

import java.rmi.Naming;
import java.util.List;
import com.mycompany.trabalhoso.model.*;

public class ServiceConta {

    public ServiceConta() {
    }

    public static boolean criarConta(Conta conta) {

        if (Verify.identificadorUnico(conta.getIdentificador()) && Verify.idClienteExiste(conta.getIdCliente())) {
            return false;
        }
        conta.setSaldo(1000);
        // ModelAPI bd = null;
        try {

            ModelAPI bd = (ModelAPI) Naming.lookup("rmi://localhost:1099/bancoDados");
            return bd.writeArq(conta, "src/bd/contas");

        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarConta(Conta contaNova) {
        List<Conta> contas = getAllContas();
        for (Conta conta : contas) {
            if (conta.getId() == contaNova.getId()) {
                conta.setConta(contaNova);
            }
            try {
                ModelAPI bd = (ModelAPI) Naming.lookup("rmi://localhost:1099/bancoDados");
                if (bd.writeArq(conta, "src/bd/contas")) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    //testa kk
    public static boolean realizarLogin(String identificador, String senha) {
        return getAllContas().stream()
                .anyMatch(conta -> conta.getIdentificador().equals(identificador) && conta.getSenha().equals(senha));
    }

    public static Conta getConta(int id) {
        return getAllContas().stream()
                .filter(conta -> conta.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static Conta getContaCliente(int idCliente) {
        return getAllContas().stream()
                .filter(conta -> conta.getIdCliente()==idCliente)
                .findFirst()
                .orElse(null);
    }

    public static List<Conta> getAllContas() {
        try {
            ModelAPI bd = (ModelAPI) Naming.lookup("rmi://localhost:1099/bancoDados");
            return bd.readArqConta();
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static int getNextId() {
        return getAllContas()
                .stream()
                .map(contas -> contas.getId())
                .max(Integer::compare)
                .orElse(0)
                + 1;
    }

}
