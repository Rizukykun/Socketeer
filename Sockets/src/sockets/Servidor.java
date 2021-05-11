/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Servidor extends Thread
{
    ServerSocket server;
    String arquivo;

    public Servidor() throws IOException
    {
        this.server = new ServerSocket(1234);
    }
    
    @Override
    public void run() {
        while(true){//Ver depois como matar esse loop
            try
            {
                //Checar se todas as classes usadas são ThreadSafe
                Socket cliente = server.accept();
                Scanner entrada = new Scanner(cliente.getInputStream());
                arquivo = entrada.next();
                Fila.obtemInstancia().enfileirar(arquivo);
            } catch (IOException ex)
            {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
