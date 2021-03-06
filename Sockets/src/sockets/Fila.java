/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Administrador
 */
public class Fila
{
    //Singleton//
    private static Fila _instancia;
    
    private Fila()
    {
        fila = new ConcurrentLinkedQueue<String>();
    }
        
    public static Fila obtemInstancia(){
        if (_instancia == null)
            _instancia = new Fila();
        return _instancia;
    }
    //Fim do Singleton//

    
    
    ConcurrentLinkedQueue<String> fila;
    /**
     * @return the queue
     */
    public String desenfileirar()
    {
        return fila.poll();
    }
    public void enfileirar(String arquivo){
        fila.add(arquivo);
    }
    public boolean filaVazia(){
        return fila.isEmpty();
    }
}
