package com.mycompany.trabalhoso;
import java.rmi.*; 
import java.rmi.server.*; 
public class BancoServer extends UnicastRemoteObject implements BancoAPI{
    static private double montanteBanco;
    public BancoServer() throws RemoteException{
        super();
    }
}
