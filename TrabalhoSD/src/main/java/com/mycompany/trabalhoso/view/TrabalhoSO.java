package com.mycompany.trabalhoso.view;

import java.io.IOException;
import java.util.Scanner;

import com.mycompany.trabalhoso.control.*;
import com.mycompany.trabalhoso.model.*;


public class TrabalhoSO {

    public static void main(String[] args) throws IOException {
        //List <Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente cliente = new Cliente();
        cliente.setCliente(0, "pedro", "149.766.816-61");

        int opMenu = 0;
        try (Scanner teclado = new Scanner(System.in)) {
            while(opMenu != 3){

                Conta c = new Conta();
                Cliente cli = new Cliente();

                System.out.println("Banco Imobiliario");
                System.out.println("1 - Logar Conta");
                System.out.println("2 - Criar Conta");
                System.out.println("3 - Sair");
                opMenu = teclado.nextInt();

                switch (opMenu) {
                    case 1:
                        System.out.println("login");
                        System.out.println("Insira o identificador da conta:");
                        System.out.println("Insira sua senha:");

                        break;
                    case 2:
                        
                        System.out.println("criar");
                        
                        System.out.println("Insira o identificador da sua conta:");
                        c.setIdentificador(teclado.nextLine());
                        System.out.println("Insira seu nome completo:");
                        cli.setNome(teclado.nextLine());
                        System.out.println("Insira seu CPF:");
                        cli.setCPF(teclado.nextLine());
                        System.out.println("Insira sua senha:");
                        c.setSenha(teclado.nextLine());

                        System.out.println( SystemOperation.criarConta(cli,c));

                        break;
                    default:
                        break;
                }
            }
        }
    }
}
