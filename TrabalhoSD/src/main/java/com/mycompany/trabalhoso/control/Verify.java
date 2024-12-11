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
    
    public static boolean idClienteExiste(int idCliente) {
        return ServiceConta.getAllContas()
        .stream()
        .map(conta -> conta.getIdCliente())
        .anyMatch(id -> id == idCliente);
    }

}
