package com.mycompany.trabalhoso.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrabalhoSO {

    public static void main(String[] args) throws IOException {
        //List <Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente cliente = new Cliente();
        cliente.setCliente("pedro", "149.766.816-61");
        Cliente.getAllClientes().
                stream().
                forEach(c-> {
                    System.out.println(c.toString());
        });
    }
}
