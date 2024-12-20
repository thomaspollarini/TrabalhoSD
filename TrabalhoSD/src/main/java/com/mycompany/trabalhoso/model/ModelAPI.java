package com.mycompany.trabalhoso.model;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface ModelAPI extends Remote, Serializable{
    <T> boolean writeArq(T objeto, String caminhoArquivo) throws RemoteException;
    List<Cliente> readArqCliente() throws RemoteException, IOException;
    List<Conta> readArqConta() throws RemoteException ,IOException;
    List<Transacao> readArqTransacao() throws RemoteException, IOException;

}
