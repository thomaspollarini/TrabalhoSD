package com.mycompany.trabalhoso.view;

import java.io.IOException;
import java.util.Scanner;


import com.mycompany.trabalhoso.model.*;


public class TrabalhoSO {

    public static void main(String[] args) throws IOException {

        int opMenu = 0;
        try (Scanner teclado = new Scanner(System.in)) {
            while(opMenu != 3){

                System.out.println("\nBanco Imobiliario");
                System.out.println("1 - Logar Conta");
                System.out.println("2 - Criar Conta");
                System.out.println("3 - Sair");
                System.out.print("Digite o numero da operacao a ser realizada:");
                opMenu = teclado.nextInt();

                switch (opMenu) {
                    case 1:
                            loginConta();
                        break;
                    case 2:
                            criarConta();
                        break;
                    default:
                        break;
                }
            }
        }
    }
    

    public static void loginConta() throws IOException{
        Conta c = new Conta();
        boolean aut = true;
        int opMenu = 0;

        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("login");

            System.out.println("Insira o identificador da conta:");
            c.setIdentificador(teclado.nextLine());
            System.out.println("Insira sua senha:");
            c.setSenha(teclado.nextLine());

            //Autenticacao
            if(aut){
                while(opMenu != 4){

                    System.out.println("\nOperacoes bancarias");
                    System.out.println("1 - Consulta de saldo");
                    System.out.println("2 - Tranferencia para outra conta");
                    System.out.println("3 - Consulta de extrato");
                    System.out.println("4 - Sair");
                    System.out.print("Digite o numero da operacao a ser realizada:");
                    opMenu = teclado.nextInt();
    
                    switch (opMenu) {
                        case 1:
                                //Funcao Consulta de Saldo
                            break;
                        case 2:
                                transConta();
                            break;
                        case 3:
                                //Funcao Consulta de Extrato
                        default:
                            break;
                    }
                }
            }else{
                System.out.println("Id ou senha incorreta.");
            }
        }

    }

    public static void criarConta() throws IOException{
        Conta c = new Conta();
        Cliente cli = new Cliente();
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("Criando nova conta");
                                
            System.out.print("Insira o identificador da sua conta:");
            c.setIdentificador(teclado.nextLine());
            System.out.print("Insira seu nome completo:");
            cli.setNome(teclado.nextLine());
            System.out.print("Insira seu CPF:");
            cli.setCPF(teclado.nextLine());
            System.out.print("Insira sua senha:");
            c.setSenha(teclado.nextLine());
        }

        //System.out.println( SystemOperation.criarConta(cli,c));

    }

    public static void transConta(){
        int contaDestino;
        try (Scanner teclado = new Scanner(System.in)) {
            boolean aut = true;
            
            System.out.println("Tranferencia de valor");
            System.out.print("\nInsira o nome do titular da conta de destino:");
            contaDestino = teclado.nextInt();

            //Funcao de pesquisa da conta de destino
            if(aut){
                float valor;
                System.out.print("Insira o valor da tranferencia:");
                valor = teclado.nextFloat();
                //Funcao de tranferencia
            }else{
                System.out.println("Conta destino nao encontrada");
            }
        }
    }

}
