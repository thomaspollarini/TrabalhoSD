package com.mycompany.trabalhoso.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Conta{
    private int id;
    private double saldo;
    private String identificador;
    private String senha;
    private int idCliente;

    public Conta() {
    }

    public Conta(int id, double saldo, String identificador, String senha, int idCliente) {
        this.id = id;
        this.saldo = saldo;
        this.identificador = identificador;
        this.senha = senha;
        this.idCliente = idCliente;
    }
    
    
    public boolean criarConta(String identificador, String senha, int idCliente){
        this.saldo = 1000;
        boolean isUniq = Conta.getAllContas()
                .stream()
                .map(conta -> conta.getIdentificador())
                .noneMatch(ident -> ident.equals(identificador));
        
        if(!isUniq){
            return false;
        }
        this.identificador=identificador;
        
        this.senha = senha;
        this.id = Conta.getAllContas()
                .stream()
                .map(conta-> conta.getId())
                .max(Integer::compare)
                .orElse(0)
                +1;
        this.idCliente = idCliente;
        
        Conta.writeArqCliente(this);
        
        return true;
    }

    public int getId() {
        return id;
    }
    

    public double getSaldo() {
        return saldo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getSenha() {
        return senha;
    }

    public int getCliente() {
        return idCliente;
    }
    
   static public List<Conta> getAllContas() {
        try {
            return new Conta().readArqConta();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Contas");
            return null;
        }
    } 
    static private List<Conta> readArqConta() throws IOException {
        List<String> linhas = readAllLines(Paths.get("src/bd/contas"), Charset.defaultCharset());
        return linhas.stream()
                .map(linha -> linha.split(";"))
                .map(dados -> {
                    return  new Conta(
                            Integer.parseInt(dados[0]),
                            Double.parseDouble(dados[1]),
                            dados[2],
                            dados[3],
                            Integer.parseInt(dados[4])
                    );
                })
                .collect(Collectors.toList());
    }
    static private void writeArqCliente(Conta conta) {
        try (FileWriter escritor = new FileWriter("src/bd/contas", true)) {
            escritor.write(conta.toString()+"\n");
        } catch (Exception e) {
            System.out.println("Erro na escrita do arquivo cliente");
        }
    }
public String toString() {
        return String.format("%d;%.2f%,%s;%s;%d",
                this.id, 
                this.saldo,
                this.identificador,
                this.senha,
                this.idCliente
        ); 
}

}
