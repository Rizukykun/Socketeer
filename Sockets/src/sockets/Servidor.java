/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Servidor extends Thread
{
    boolean ativo;
    ServerSocket server;
    String arquivo;
    private List<Cliente> clientesConectados;

    public Servidor() throws IOException
    {
        this.clientesConectados = Collections.synchronizedList(new ArrayList<>());
        this.server = new ServerSocket(1234);
        this.ativo= true;
    }
    
    @Override
    public void run() {
        while(ativo){//Ver depois como matar esse loop
            try
            {
                //Checar se todas as classes usadas são ThreadSafe
                Socket socketCliente = server.accept();
                Cliente cliente = new Cliente(socketCliente);
                cliente.start();
                clientesConectados.add(cliente);
                /*
                Scanner entrada = new Scanner(cliente.getInputStream());
                arquivo = entrada.next();
                Fila.obtemInstancia().enfileirar(arquivo);
                 */
            } catch (IOException ex)
            {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fechar() throws IOException{
        ativo = false;
        for(Cliente c : clientesConectados){
            System.out.println("Fechando conexão com o cliente " + c.hashCode());
            c.fecha();
        }
    }
    
    public int getPort(){
        return server.getLocalPort();
    }
    
    public void listarClientes(){
        for(Cliente c : clientesConectados){
            System.out.println("Cliente " + c.getIdentificacao());
        }
    }
}
