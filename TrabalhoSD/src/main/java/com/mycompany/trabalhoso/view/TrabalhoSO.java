package com.mycompany.trabalhoso.view;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Scanner;

import com.mycompany.trabalhoso.control.ControlAPI;
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

            try{
                ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
                aut = control.realizarLogin(c.getIdentificador(), c.getSenha());
            }
            catch (Exception e) {
                System.out.println("Erro ao criar a conta: " + e.getMessage()); 
                e.printStackTrace();
            }

            if(aut){
                while(opMenu != 5){

                    System.out.println("\nOperacoes bancarias");
                    System.out.println("1 - Consulta de saldo");
                    System.out.println("2 - Tranferencia para outra conta");
                    System.out.println("3 - Consulta de extrato");
                    System.out.println("4 - Pesquisar contas por nome");
                    System.out.println("5 - Sair");
                    System.out.print("Digite o numero da operacao a ser realizada:");
                    opMenu = teclado.nextInt();
    
                    switch (opMenu) {
                        case 1:
                            try{
                                ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
                                System.out.println(control.consultarSaldo(c.getId()));
                            }
                            catch (Exception e) {
                                System.out.println("Erro ao criar a conta: " + e.getMessage()); 
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                                transConta(c.getId());
                            break;
                        case 3:
                            try{
                                ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
                                System.out.println(control.consultarExtrato(c.getId()));
                            }
                            catch (Exception e) {
                                System.out.println("Erro ao criar a conta: " + e.getMessage()); 
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                                consultarConta();
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

        try{
            ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
            System.out.println(control.criarConta(cli,c));
        }
        catch (Exception e) {
            System.out.println("Erro ao criar a conta: " + e.getMessage()); 
            e.printStackTrace();
        }

    }

    public static void transConta(int contaSaida) throws IOException{
        int contaDestino;
        try (Scanner teclado = new Scanner(System.in)) {
            
            System.out.println("Tranferencia de valor");
            System.out.print("\nInsira o id da conta de destino:");
            contaDestino = teclado.nextInt();

            float valor;
            System.out.print("Insira o valor da tranferencia:");
            valor = teclado.nextFloat();
            
            try{
                ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
                System.out.println(control.transferir(contaSaida, contaDestino, valor));
            }
            catch (Exception e) {
                System.out.println("Erro ao criar a conta: " + e.getMessage()); 
                e.printStackTrace();
            }
        }
    }

    public static void consultarConta(){
        String nomeConta;
        try (Scanner teclado = new Scanner(System.in)) {
            
            System.out.println("Consulta de id da conta por nome");
            System.out.print("\nInsira o nome do titular da conta:");
            nomeConta = teclado.nextLine();

            try{
                ControlAPI control = (ControlAPI) Naming.lookup("rmi://localhost/control");
                System.out.println(control.pesquisarContas(nomeConta));
            }
            catch (Exception e) {
                System.out.println("Erro ao criar a conta: " + e.getMessage()); 
                e.printStackTrace();
            }
        }
    }

}
