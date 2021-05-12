/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio
 */
public class Cliente extends Thread {

    private Socket clienteSocket;
    private Scanner scanner;
    private String status;

    public Cliente(Socket clienteSocket) throws IOException {
        this.clienteSocket = clienteSocket;
        this.scanner = new Scanner(clienteSocket.getInputStream());
        this.status = "Aberta";
    }

    @Override
    public void run() {
        System.out.println("Conexão com cliente " + clienteSocket.hashCode() + " estabelecida.");
        while (scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            Fila.obtemInstancia().enfileirar(entrada);
            System.out.println("Dados recebidos do cliente " + clienteSocket.hashCode());
        }
        status = "Fechada";
        
        try {
            fecha();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fecha() throws IOException {
        clienteSocket.close();
        scanner.close();
    }
    
    public String getIdentificacao(){
        return clienteSocket.hashCode() + " - " + status;
    }
}
