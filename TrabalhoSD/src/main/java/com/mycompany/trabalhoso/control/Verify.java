package com.mycompany.trabalhoso.control;

public class Verify {
    
    public Verify() {
    }

    public static boolean identificadorUnico(String identificador) {
        return ServiceConta.getAllContas()
        .stream()
        .map(conta -> conta.getIdentificador())
        .noneMatch(ident -> ident.equals(identificador));
    }

    public static boolean cpfUnico(String cpf) {
        return ServiceCliente.getAllClientes()
        .stream()
        .map(cliente -> cliente.getCPF())
        .noneMatch(cpfCliente -> cpfCliente.equals(cpf));
    }
    
    public static boolean idClienteExiste(int idCliente) {
        return ServiceConta.getAllContas()
        .stream()
        .map(cliente -> cliente.getIdCliente())
        .anyMatch(id -> id == idCliente);
    }

    public static boolean idContaExiste(int idConta) {
        return ServiceConta.getAllContas()
        .stream()
        .map(conta -> conta.getId())
        .anyMatch(id -> id == idConta);
    }

}
